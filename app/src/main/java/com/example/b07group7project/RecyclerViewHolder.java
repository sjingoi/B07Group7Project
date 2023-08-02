package com.example.b07group7project;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewHolder<ItemType> extends RecyclerView.ViewHolder {
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void updateView(ItemType item);
}