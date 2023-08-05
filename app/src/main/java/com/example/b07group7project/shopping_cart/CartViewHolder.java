package com.example.b07group7project.shopping_cart;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.b07group7project.ImageDownloader;
import com.example.b07group7project.R;
import com.example.b07group7project.RecyclerViewHolder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CartViewHolder extends RecyclerViewHolder<CartItem> {

    ImageView imageView;
    TextView nameView, quantityView;
    ImageButton increaseQty, decreaseQty;
    View layout;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.cartItemImage);
        nameView = itemView.findViewById(R.id.cartItemName);
        quantityView = itemView.findViewById(R.id.cartItemQty);
        increaseQty = itemView.findViewById(R.id.increateQty);
        decreaseQty = itemView.findViewById(R.id.decreaseQty);
        layout = itemView.findViewById(R.id.itemLayout);

    }

    @Override
    public void updateView(CartItem cartItem) {
        nameView.setText(cartItem.getProductName());
        quantityView.setText(String.format(Integer.toString(cartItem.getQuantity())));

        increaseQty.setOnClickListener(view -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            quantityView.setText(String.format(Integer.toString(cartItem.getQuantity())));
        });

        decreaseQty.setOnClickListener(view -> {
            if (cartItem.getQuantity() != 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                quantityView.setText(String.format(Integer.toString(cartItem.getQuantity())));
            }
        });

        layout.setOnClickListener(cartItem.onClickListener);






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
