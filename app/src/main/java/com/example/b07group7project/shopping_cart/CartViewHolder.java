package com.example.b07group7project.shopping_cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.RecyclerViewHolder;

public class CartViewHolder extends RecyclerViewHolder {

    ImageView imageView;
    TextView nameView, quantityView;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cartItemImage);
        nameView = itemView.findViewById(R.id.cartItemName);
        quantityView = itemView.findViewById(R.id.cartItemQty);
    }

    @Override
    public void updateView(Object item) {
        if (item instanceof CartItem) {
            CartItem cartItem = (CartItem)item;
            nameView.setText(cartItem.getProductName());
            quantityView.setText(String.format(Integer.toString(cartItem.getQuantity())));
            imageView.setBackgroundResource(cartItem.getImage());
        }
    }


}
