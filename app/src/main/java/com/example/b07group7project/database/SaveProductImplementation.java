package com.example.b07group7project.database;

import com.example.b07group7project.create_product.SaveProduct;
import com.example.b07group7project.database.DataSetter;
import com.example.b07group7project.database.Database;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class SaveProductImplementation extends Database implements SaveProduct {

    @Override
    public void saveProductToFirebase(String itemName, String itemDesc, String itemURL, String storeID, double itemPrice) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        String itemID = UUID.randomUUID().toString(); // Generate a unique key for the product

        // Save the product information to the Products node
        DatabaseReference productsRef = databaseRef.child("Products")
                .child(storeID)
                .child(itemID);

        // Use the put method to set the product information
        putProductInfo(productsRef, itemName, itemDesc, itemURL, itemPrice);
    }

    private void putProductInfo(DatabaseReference productRef, String name, String description, String image, double price) {
        // Use the put method with DataSetter to set each field's value
        put(productRef.child("name"), snapshot -> name);
        put(productRef.child("description"), snapshot -> description);
        put(productRef.child("image"), snapshot -> image);
        put(productRef.child("price"), snapshot -> price);
    }
}