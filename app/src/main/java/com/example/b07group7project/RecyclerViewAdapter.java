package com.example.b07group7project;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class RecyclerViewAdapter<ItemType, ViewHolderType extends RecyclerViewHolder> extends RecyclerView.Adapter<ViewHolderType> {
    protected Context context;
    protected List<ItemType> items;

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
