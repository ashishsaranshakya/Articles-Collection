package com.ashish.articlescollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ArticleActivity extends AppCompatActivity {

    private TextView title;
    private TextView content;
    private RequestQueue queue;
    private String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        title=findViewById(R.id.article_title);
        content=findViewById(R.id.article_content);
        queue = Volley.newRequestQueue(this);

        Intent intent=getIntent();
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));
        _id=intent.getStringExtra("_id");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        if (itemId==R.id.menu_edit_article){
            editArticle();
            return true;
        }
        else if (itemId==R.id.menu_delete_article){
            deleteArticle();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    void deleteArticle(){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, MainActivity.apiUrl+"article/id/"+_id,
                response -> {
                    Toast.makeText(ArticleActivity.this, "Article deleted successfully", Toast.LENGTH_SHORT).show();
                    closeActivity();
                }, error -> {
            Toast.makeText(ArticleActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
        });
        queue.add(stringRequest);
    }

    void editArticle(){
        Intent intent=new Intent(ArticleActivity.this,EditArticleActivity.class);
        intent.putExtra("title",title.getText().toString());
        intent.putExtra("content",content.getText().toString());
        intent.putExtra("_id",_id);
        startActivity(intent);
    }

    void closeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}