package com.example.b07group7project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.shopping_cart.CartItem;
import com.example.b07group7project.shopping_cart.CartViewHolder;

import java.util.List;

public abstract class RecyclerViewAdapter<ItemType, ViewHolderType extends RecyclerViewHolder> extends RecyclerView.Adapter<ViewHolderType> {
    Context context;
    List<ItemType> items;

    @NonNull
    @Override
    public abstract ViewHolderType onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public RecyclerViewAdapter(Context context, List<ItemType> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderType holder, int position) {
        ItemType item = items.get(position);
        holder.updateView(item); // TODO Create Item Class
    }

}
