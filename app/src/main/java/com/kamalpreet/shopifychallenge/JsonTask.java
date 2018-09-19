package com.kamalpreet.shopifychallenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JsonTask extends AsyncTask<String, String, String> {

    String result;
    Context c;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<String> tagList = new ArrayList<>();
    ArrayList<String> productList = new ArrayList<>();
    ArrayList<String> productTagList = new ArrayList<>();
    ArrayList<String> inventoryQuantity = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();



    public JsonTask(Context c, RecyclerView recyclerView) {
        this.c = c;
        this.recyclerView = recyclerView;
    }

    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                buffer.append(line+"\n");

            }
            result = buffer.toString();

            try {

                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("products");

                for (int i=0; i < jArray.length(); i++)
                {
                    try {
                        JSONObject oneObject = jArray.getJSONObject(i);


                        // Pulling items from the array
                        String tags = oneObject.getString("tags");
                        String title = oneObject.getString("title");
                        String array[] = tags.split(",");
                        for (int j = 0; j < array.length; j++) {

                            if(!tagList.contains(array[j]))
                            {
                                tagList.add(array[j]);

                            }
                            productList.add(title);
                            productTagList.add(array[j]);

                            //Inventory Images
                            JSONObject imageObject = oneObject.getJSONObject("image");
                            String imageSrc = imageObject.getString("src");
                            URL imageUrl = new URL(imageSrc);
                            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                            images.add(bmp);

                        }

                        //Inventory Counts
                        int inventoryQ = 0;
                        JSONArray inventoryArray = oneObject.getJSONArray("variants");

                        for (int a=0; a < inventoryArray.length(); a++)
                        {
                            JSONObject countObject = inventoryArray.getJSONObject(a);
                            inventoryQ += Integer.parseInt(countObject.getString("inventory_quantity"));

                        }
                        inventoryQuantity.add(String.valueOf(inventoryQ));


                    } catch (JSONException e) {
                        Log.e("Shopify Challenge", "unexpected JSON exception", e);
                    }

                }

            } catch (JSONException e) {
                Log.e("Shopify Challenge", "unexpected JSON exception", e);

            }



            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        adapter = new MyRecyclerViewAdapter(c, tagList);
        recyclerView.setAdapter(adapter);
        Data data = Data.getInstance();
        data.setTagList(tagList);
        data.setInventoryQuantity(inventoryQuantity);
        data.setProductList(productList);
        data.setProductTagList(productTagList);
        data.setImages(images);

    }

}
