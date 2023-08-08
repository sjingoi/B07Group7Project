package com.example.b07group7project.database;
import com.google.firebase.database.DatabaseReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SaveProductImplementation extends Database implements SaveProduct {

    private DatabaseReference productsRef;

    @Override
    public void saveProductToFirebase(String itemName, String itemDesc, String itemURL, String storeID, double itemPrice) {
        String itemID = UUID.randomUUID().toString(); // Generate a unique key for the product

        // Save the product information to the Products node
        productsRef = root.child("Products")
                .child(storeID)
                .child(itemID);

        // Create a HashMap to store the product information
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", itemName);
        productInfo.put("description", itemDesc);
        productInfo.put("image", itemURL);
        productInfo.put("price", itemPrice);

        // Use the put method to set the product information
        putProductInfo(productInfo);
    }

    private void putProductInfo(Map<String, Object> productInfo) {
        // Use the put method with DataSetter to set the entire product information
        put(productsRef, snapshot -> productInfo);
    }
}