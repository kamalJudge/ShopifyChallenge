package com.kamalpreet.shopifychallenge;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdaptorProducts extends RecyclerView.Adapter<RecyclerAdaptorProducts.ViewHolder> {
    private List<String> mData , mData2 ;
    private List<Bitmap> mData3;
    private LayoutInflater mInflater;
    private RecyclerAdaptorProducts.ItemClickListener mClickListener;


    // data is passed into the constructor
    RecyclerAdaptorProducts(Context context, List<String> data , List<String> data2 ,  List<Bitmap> data3){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mData2 = data2;
        this.mData3 = data3;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerAdaptorProducts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_products, parent, false);
        return new RecyclerAdaptorProducts.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(RecyclerAdaptorProducts.ViewHolder holder, int position) {
        String productNmae = mData.get(position);
        String quantity = mData2.get(position);
        Bitmap imageBmp = mData3.get(position);
        holder.myTextView.setText("Name : "+productNmae);
        holder.myTextViewQuantity.setText("Quantity : "+quantity);
        holder.imageView.setImageBitmap(imageBmp);


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView , myTextViewQuantity;
        private ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.productName);
            myTextViewQuantity = itemView.findViewById(R.id.productQuantity);
            imageView = itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}