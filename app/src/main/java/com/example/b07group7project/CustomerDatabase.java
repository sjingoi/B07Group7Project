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
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
    }

    public void addCustomer(Customer customer) {
        UUID uuid = UUID.randomUUID();
        DatabaseReference newreference = reference.child("Customers").child(uuid.toString());
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

    public void addPreviousOrder(String email, PreviousOrder previousOrder){
        reference.child("Customers");
        UUID uuid = UUID.randomUUID();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap h = previousOrder.putIntoHashMap();
                if (!snapshot.exists()) {
                    reference.child(uuid.toString()).setValue(h);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
