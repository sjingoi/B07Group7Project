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
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }

    public PreviousOrder(String email, String accounttype){
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
        products = new HashMap<>();

    }

    public static List<PreviousOrder> getPreviousOrders(DatabaseReference ref, String email) {
        List<PreviousOrder> previousOrders = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PreviousOrder previousOrder;
                for (DataSnapshot snap : snapshot.getChildren()) {
                    UUID uuid = fromString(snap.getKey());
                    previousOrder = new PreviousOrder(email, uuid.toString());
                    previousOrders.add(previousOrder);
                    previousOrder = null;
                    uuid = null;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return previousOrders; /////////Change this

    }

    public HashMap<String, Object> putIntoHashMap() {
        HashMap<String, Object> hashmap = new HashMap<>();
        for (String s : hashmap.keySet()) {
            UUID uuid = UUID.randomUUID();
            hashmap.put(uuid.toString(), hashmap.get(s));
        }
        return hashmap;
    }
}
