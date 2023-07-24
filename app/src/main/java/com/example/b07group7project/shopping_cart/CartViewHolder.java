package com.example.b07group7project.shopping_cart;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.ImageDownloader;
import com.example.b07group7project.R;
import com.example.b07group7project.RecyclerViewHolder;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<Bitmap> future = executorService.submit(new ImageDownloader(cartItem.imageURL));
            try {
                Bitmap bitmap = future.get();
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            executorService.shutdown();

        }
    }


}
