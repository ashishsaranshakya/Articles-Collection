package com.ashish.articlescollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditArticleActivity extends AppCompatActivity {

    private RequestQueue queue;
    EditText title;
    EditText content;
    Button updateArticle;
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        title=findViewById(R.id.updatetext_title);
        content=findViewById(R.id.updatetext_content);
        updateArticle =findViewById(R.id.btn_update_article);
        queue = Volley.newRequestQueue(this);

        Intent intent=getIntent();
        _id=intent.getStringExtra("_id");
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));

        updateArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText=title.getText().toString();
                String contentText=content.getText().toString();
                updateNewArticle(titleText,contentText);
            }
        });

    }

    void updateNewArticle(String titleText, String contentText){
        if(titleText.equals("") || contentText.equals("")){
            Toast.makeText(this, "No field can be left blank", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, MainActivity.apiUrl+"article/id/"+_id,
                response -> {
                    Toast.makeText(EditArticleActivity.this, "Article updated successfully", Toast.LENGTH_SHORT).show();
                    closeActivity();
                }, error -> {
            Toast.makeText(EditArticleActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("Content-Type", "application/x-www-form-urlencoded");
                return pars;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> pars = new HashMap<String, String>();
                pars.put("title", titleText);
                pars.put("content", contentText);
                return pars;
            }
        };
        queue.add(stringRequest);
    }

    void closeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title",title.getText().toString());
        startActivity(intent);
        finish();
    }
}