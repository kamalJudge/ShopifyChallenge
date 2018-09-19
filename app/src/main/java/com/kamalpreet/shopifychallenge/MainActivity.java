package com.kamalpreet.shopifychallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    
    JsonTask jsonTask;
    String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvTags);
        jsonTask = new JsonTask(this, recyclerView);
        jsonTask.execute(url);


    }

}