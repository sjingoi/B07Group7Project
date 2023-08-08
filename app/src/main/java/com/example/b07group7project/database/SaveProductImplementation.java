package com.example.b07group7project.database;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SaveProductImplementation extends Database implements SaveProduct {

    private DatabaseReference productsRef;
    private Context context; // Add a member variable to hold the context

    // Constructor to initialize the context
    public SaveProductImplementation(Context context) {
        this.context = context;
    }

    @Override
    public void saveProductToFirebase(String itemName, String itemDesc, String itemURL, String storeID, double itemPrice) {
        String itemID = UUID.randomUUID().toString(); // Generate a unique key for the product

        // Save the product information to the Products node
        productsRef = root.child(Constants.products)
                .child(storeID)
                .child(itemID);

        // Create a HashMap to store the product information
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put(Constants.product_name, itemName);
        productInfo.put(Constants.product_description, itemDesc);
        productInfo.put(Constants.product_image, itemURL);
        productInfo.put(Constants.product_price, itemPrice);

        // Use the put method to set the product information
        putProductInfo(productInfo);

        // Show a toast message when the item is created
        showToast("Item created successfully.");
    }

    private void putProductInfo(Map<String, Object> productInfo) {
        // Use the put method with DataSetter to set the entire product information
        put(productsRef, snapshot -> productInfo);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}