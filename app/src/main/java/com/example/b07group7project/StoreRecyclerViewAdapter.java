package com.example.b07group7project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreRecyclerViewAdapter extends RecyclerViewAdapter<Store, StoreRecyclerViewAdapter.MyViewHolder>{


    public StoreRecyclerViewAdapter(Context context, List<Store> items) {
        super(context, items);
    }

    @NonNull
    public StoreRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Give Look To Each Row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.storelist_row_data, parent, false);

        return new StoreRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreRecyclerViewAdapter.MyViewHolder holder, int position) {
        // Assign Value to Each Row as They Come On Screen

        holder.tvName.setText(items.get(position).getStoreName());
        holder.tvBio.setText(items.get(position).getStoreName());
        holder.imageView.setImageResource(items.get(position).getImage());

    }

    public static class MyViewHolder extends RecyclerViewHolder<Store> {

        ImageView imageView;
        TextView tvName, tvBio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            tvName = itemView.findViewById(R.id.Store_Name);
            tvBio = itemView.findViewById(R.id.Store_Bio);
        }

        @Override
        public void updateView(Store item) {

        }


    }


}
