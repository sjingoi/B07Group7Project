package com.example.b07group7project.shopping_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.b07group7project.R;
import com.example.b07group7project.RecyclerViewAdapter;

import java.util.List;

public class CartAdapter extends RecyclerViewAdapter<CartEntry, CartViewHolder> {

    List<CartEntry> entries;

    EntryClickListener entryClickListener;


    public CartAdapter(Context context, List<CartEntry> items, EntryClickListener entryClickListener) {
        super(context, items);
        this.entryClickListener = entryClickListener;
        this.entries = items;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.layout.setOnClickListener(view -> entryClickListener.onEntryClick(entries.get(position)));
        holder.removeItem.setOnClickListener(view -> entryClickListener.onRemoveClick(position)
        );
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item_view, parent, false));
    }
}
