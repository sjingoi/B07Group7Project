package com.example.b07group7project.shopping_cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    Context context;
    List<CartItem> items;

    public CartAdapter(Context context, List<CartItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.nameView.setText(item.getProductName());
        holder.quantityView.setText(String.format(Integer.toString(item.getQuantity())));
        holder.imageView.setBackgroundResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
