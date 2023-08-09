package com.example.b07group7project.database;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.b07group7project.database.CartListener;
import com.example.b07group7project.database.Constants;
import com.example.b07group7project.database.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CartListenerImplementation extends Database implements CartListener {

    private DatabaseReference cartRef;
    private Context context;

    public CartListenerImplementation(Context context) {
        this.context = context;
    }

    @Override
    public void addToCart(String store_uuid, String customer_uuid, String itemID, int quantity) {
        String cartEntry = store_uuid + ":" + itemID;

        // Customer cart information to Customers node
        cartRef = root.child(Constants.customers)
                .child(customer_uuid)
                .child(Constants.shopping_cart)
                .child(cartEntry);

        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Item already exists in the cart, update the quantity
                    int existingQuantity = dataSnapshot.child(Constants.quantity).getValue(Integer.class);
                    int newQuantity = existingQuantity + quantity;

                    Map<String, Object> updatedQuantity = new HashMap<>();
                    updatedQuantity.put(Constants.quantity, newQuantity);

                    cartRef.updateChildren(updatedQuantity);

                    showToast("Item quantity updated in the cart.");
                } else {
                    // Item doesn't exist in the cart, add it with the given quantity
                    Map<String, Object> cartItem = new HashMap<>();
                    cartItem.put(Constants.quantity, quantity);

                    put(cartRef, snapshot -> cartItem);

                    showToast("Item added to cart successfully.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showToast("Failed to add item to cart.");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

