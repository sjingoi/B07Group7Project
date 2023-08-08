package com.example.b07group7project.create_product;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class SaveProductImplementation implements SaveProduct {

    @Override
    public void saveProductToFirebase(String itemName, String itemDesc, String itemURL, String storeID, double itemPrice) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        String itemID = UUID.randomUUID().toString(); // Generate a unique key for the product

        // Save the product information to the Products node
        DatabaseReference productsRef = databaseRef.child("Products")
                .child(storeID)
                .child(itemID);

        productsRef.child("name").setValue(itemName);
        productsRef.child("description").setValue(itemDesc);
        productsRef.child("image").setValue(itemURL);
        productsRef.child("price").setValue(itemPrice);
    }
}