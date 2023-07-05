package com.ashish.articlescollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String apiUrl="https://articles-api-o6ao.onrender.com/";

    private RequestQueue queue;
    private TextView textView;
    private List<Article> articlesList=new ArrayList<>();
    private final Map<String,Article> articleMap=new HashMap<>();
    private final List<String> titleList=new ArrayList<>();
    private FloatingActionButton fabAddArticle;
    private ListView articlesListView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.txt);
        fabAddArticle=findViewById(R.id.fab_add_article);
        articlesListView=findViewById(R.id.articleList);
        queue = Volley.newRequestQueue(this);
        adapter= new ArrayAdapter<>(this, R.layout.list_item, titleList);
        articlesListView.setOnItemClickListener((adapterView, view, i, l) -> {
            String title=((TextView) view).getText().toString();
            Intent intent=new Intent(MainActivity.this,ArticleActivity.class);
            Article article=articleMap.get(title);
            assert article != null;
            intent.putExtra("title",title)
                    .putExtra("content",article.getContent())
                    .putExtra("_id",article.get_id());
            startActivity(intent);
        });
        articlesListView.setAdapter(adapter);
        getAllArticles(adapter);

        fabAddArticle.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,NewArticleActivity.class)));

    }

    void getAllArticles(ArrayAdapter<String> adapter){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl+"articles",
                response -> {
                    Gson gson = new Gson();
                    Type objectType = new TypeToken<List<Article>>(){}.getType();
                    articlesList = gson.fromJson(response, objectType);
                    titleList.clear();
                    for(Article article:articlesList){
                        articleMap.put(article.getTitle(),article);
                        titleList.add(article.getTitle());
                    }
                    adapter.notifyDataSetChanged();
                    toHistory();
                }, error -> textView.append("That didn't work!"));
        queue.add(stringRequest);
    }

    void toHistory(){
        if(getIntent().hasExtra("title")){
            String title=getIntent().getStringExtra("title");
            getIntent().removeExtra("title");
            Intent intent=new Intent(MainActivity.this,ArticleActivity.class);
            Article article=articleMap.get(title);
            assert article!=null;
            intent.putExtra("title",title).putExtra("content",article.getContent());
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) search.getActionView();
        searchView.setQueryHint("Article title");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s.trim());
                return false;
            }
        });
        return true;
    }

}