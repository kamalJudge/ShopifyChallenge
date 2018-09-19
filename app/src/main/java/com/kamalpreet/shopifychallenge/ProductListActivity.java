package com.kamalpreet.shopifychallenge;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    Data data;
    ArrayList<String> name, quantity;
    ArrayList<Bitmap> image;
    int rowPosition;
    String tagName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        data = Data.getInstance();

        quantity = data.getInventoryQuantity();
        name = new ArrayList<>();
        image = new ArrayList<>();

        // receiving row position to get respective tag
        rowPosition = getIntent().getIntExtra("position", 0);
        tagName = data.getTagList().get(rowPosition);

        //function to get product name and images
        getProductsContainsTag();

        RecyclerView recyclerView = findViewById(R.id.rvProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdaptorProducts adapter = new RecyclerAdaptorProducts(this, name,quantity,image);
        recyclerView.setAdapter(adapter);

    }

    private void getProductsContainsTag()
    {
        for(int a = 0 ; a<data.getProductTagList().size() ; a++)
        {
            if(data.getProductTagList().get(a).equalsIgnoreCase(tagName))
            {
                name.add(data.getProductList().get(a));
                image.add(data.getImages().get(a));
            }
        }
    }

}
