package com.example.b07group7project.shopping_cart;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.b07group7project.R;
import com.example.b07group7project.RecyclerViewHolder;
import com.example.b07group7project.database.ImageDownloader;

public class CartViewHolder extends RecyclerViewHolder<CartEntry> {

    ImageView imageView;
    TextView nameView, quantityView;
    ImageButton increaseQty, decreaseQty, removeItem;
    View layout;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cartItemImage);
        nameView = itemView.findViewById(R.id.cartItemName);
        quantityView = itemView.findViewById(R.id.cartItemQty);
        increaseQty = itemView.findViewById(R.id.increateQty);
        decreaseQty = itemView.findViewById(R.id.decreaseQty);
        layout = itemView.findViewById(R.id.itemLayout);
        removeItem = itemView.findViewById(R.id.removeItem);

    }

    @Override
    public void updateView(CartEntry cartEntry) {
        nameView.setText(cartEntry.getProduct().getItemName());
        quantityView.setText(String.format(Integer.toString(cartEntry.getQuantity())));

        increaseQty.setOnClickListener(view -> {
            cartEntry.setQuantity(cartEntry.getQuantity() + 1);
            quantityView.setText(String.format(Integer.toString(cartEntry.getQuantity())));
        });

        decreaseQty.setOnClickListener(view -> {
            if (cartEntry.getQuantity() != 1) {
                cartEntry.setQuantity(cartEntry.getQuantity() - 1);
                quantityView.setText(String.format(Integer.toString(cartEntry.getQuantity())));
            }
        });

        removeItem.setOnClickListener(view -> {
            //Stuff here
        });


        ImageDownloader.setImageResource(imageView, cartEntry.getProduct().getImageURL(), 256, 256);
    }
}
