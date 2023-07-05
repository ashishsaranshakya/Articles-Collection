package com.ashish.articlescollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NewArticleActivity extends AppCompatActivity {

    private RequestQueue queue;
    EditText title;
    EditText content;
    Button addArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        title=findViewById(R.id.edittext_title);
        content=findViewById(R.id.edittext_content);
        addArticle=findViewById(R.id.btn_add_article);
        queue = Volley.newRequestQueue(this);

        addArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText=title.getText().toString();
                String contentText=content.getText().toString();
                Log.w("New Article",titleText+" "+contentText);
                addNewArticle(titleText,contentText);
            }
        });

    }

    void addNewArticle(String titleText, String contentText){
        if(titleText.equals("") || contentText.equals("")){
            Toast.makeText(this, "No field can be left blank", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, MainActivity.apiUrl+"article",
                response -> {
                    Toast.makeText(NewArticleActivity.this, "New article added successfully", Toast.LENGTH_SHORT).show();
                    closeActivity();
                }, error -> {
                    Toast.makeText(NewArticleActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
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
        startActivity(intent);
        finish();
    }
}