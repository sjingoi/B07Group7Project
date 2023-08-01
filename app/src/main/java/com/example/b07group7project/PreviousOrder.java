package com.example.b07group7project;

import static java.util.UUID.fromString;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PreviousOrder {
    FirebaseDatabase database;
    DatabaseReference reference;
    HashMap<String, Object> products;

    public PreviousOrder() {
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
    }

    public PreviousOrder(String email, UUID uuid){
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
        products = new HashMap<>();
        DatabaseReference newreference = reference.child("Customer").child("Previous Orders").child("Products"); // change****************
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 1;
                for (DataSnapshot snap: snapshot.getChildren()) {
                    Product p = new Product((String) snap.child("Name").getValue(), (String) snap.child("Description").getValue(), (String) snap.child("Price").getValue());
                    products.put(Integer.toString(i), p);
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static List<PreviousOrder> getPreviousOrders(DatabaseReference ref, String email) {
        List<PreviousOrder> previousOrders = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PreviousOrder previousOrder;
                for (DataSnapshot snap : snapshot.getChildren()) {
                    UUID uuid = fromString(snap.getKey());
                    previousOrder = new PreviousOrder(email, uuid);
                    previousOrders.add(previousOrder);
                    previousOrder = null;
                    uuid = null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return previousOrders;
    }
}
