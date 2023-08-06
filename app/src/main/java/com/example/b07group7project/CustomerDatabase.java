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
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    newreference.setValue(Integer.toString(quantity));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }




}
