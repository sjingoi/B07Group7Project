package com.example.b07group7project.shopper_view_store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database.ImageDownloader;

import java.util.ArrayList;


public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.MyViewHolder>{
    Context context;
    ArrayList<Store> items;
    StoreClickListener listener;

    public StoreRecyclerViewAdapter(Context context, ArrayList<Store> items, StoreClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
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
        Store store = items.get(position);
        holder.tvName.setText(store.getStoreName());
        ImageDownloader.setImageResource(holder.imageView, store.getImage(), 900, 900);

        holder.cardView.setOnClickListener(view -> listener.onStoreClicked(store));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.Product_Image);
            tvName = itemView.findViewById(R.id.Product_Name);
            cardView = itemView.findViewById(R.id.ProductCardView);
        }

    }
}
