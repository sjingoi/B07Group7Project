package com.example.b07group7project;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.UUID;

public class CustomerDatabase {
    FirebaseDatabase database;
    DatabaseReference reference;

    public CustomerDatabase() {
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }

    public void addCustomer(Customer customer) {
        DatabaseReference newreference = reference.child(Constants.customers).child(customer.uuid);
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    HashMap<String, Object> hashmap = customer.putIntoHashMap();
                    newreference.setValue(hashmap);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void addToShoppingCart(Customer customer, Product product, int quantity) {
        DatabaseReference newreference = reference.child(Constants.customers).child(customer.uuid).child(Constants.shopping_cart).child(product.uuid);
        newreference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put(Constants.quantity, Integer.toString(quantity));
                    hashmap.put(Constants.store_uuid, product.storeuuid);
                    newreference.setValue(hashmap);
                }
                else {
                    String currentQuantity = (String) snapshot.child(Constants.quantity).getValue();
                    int i = Integer.parseInt(currentQuantity);
                    i += quantity;
                    newreference.child(Constants.quantity).setValue(Integer.toString(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addPreviousOrder(Customer customer, PreviousOrder previousOrder){
        DatabaseReference newreference = reference.child(Constants.customers).child(customer.uuid).child(Constants.shopping_cart);
        newreference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
