package com.example.b07group7project.database;

import android.content.Context;
import android.widget.Toast;

import com.example.b07group7project.database.CartListener;
import com.example.b07group7project.database.Constants;
import com.example.b07group7project.database.Database;
import com.google.firebase.database.DatabaseReference;

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
        String cartEntry = store_uuid + ", " + itemID;

        //Customer cart information to Customers node
        cartRef = root.child(Constants.customers)
                .child(customer_uuid)
                .child(Constants.shopping_cart)
                .child(cartEntry);

        Map<String, Object> Quantity = new HashMap<>();
        Quantity.put(Constants.quantity, quantity);

        put(cartRef, snapshot -> Quantity);

        showToast("Item added to cart successfully.");
        }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    }

