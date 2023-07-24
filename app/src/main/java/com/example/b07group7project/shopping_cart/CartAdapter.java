package com.example.b07group7project.shopping_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.RecyclerViewAdapter;

import java.util.List;

public class CartAdapter extends RecyclerViewAdapter<CartItem, CartViewHolder> {

    public CartAdapter(Context context, List<CartItem> items) {
        super(context, items);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item_view, parent, false));
    }
}
