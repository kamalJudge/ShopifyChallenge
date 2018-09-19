package com.kamalpreet.shopifychallenge;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class Data
{
    private static Data data = new Data( );

    public static Data getInstance( ) {
        return data;
    }

    ArrayList<String> tagList = new ArrayList<>();
    ArrayList<String> productList = new ArrayList<>();
    ArrayList<String> productTagList = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> inventoryQuantity = new ArrayList<>();

    public ArrayList<Bitmap> getImages() {
        return images;
    }

    public void setImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public ArrayList<String> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<String> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<String> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<String> productList) {
        this.productList = productList;
    }

    public ArrayList<String> getProductTagList() {
        return productTagList;
    }

    public void setProductTagList(ArrayList<String> productTagList) {
        this.productTagList = productTagList;
    }

    public ArrayList<String> getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(ArrayList<String> inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }



}
