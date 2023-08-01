package com.example.b07group7project;

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

public class StoreDatabase {
    FirebaseDatabase database;
    DatabaseReference reference;

    public StoreDatabase(){
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
    }


    public void addStore(Store store){
        DatabaseReference newreference = reference.child("Stores");
        UUID uuid = UUID.randomUUID();
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> storemap = store.storeIntoHashmap();
                if (!snapshot.exists()){
                    newreference.child(uuid.toString()).setValue(storemap);
                }
                else {
                    newreference.child(uuid.toString()).updateChildren(storemap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addProductToStore(Product product, String storename){
        UUID uuid = UUID.randomUUID();
        DatabaseReference newreference = reference.child("Stores").child(storename).child("Products");
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> finalproduct = product.putIntoHashMap();
                if (!snapshot.exists()){
                    newreference.child(uuid.toString()).setValue(finalproduct);
                }
                else {
                    newreference.child(uuid.toString()).updateChildren(finalproduct);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public HashMap<String, Object> getProductsForStore(String store) {
        DatabaseReference newreference = reference.child("Stores").child(store).child("Products");
        HashMap<String, Object> products = new HashMap<>();
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> product = new HashMap<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    product.put("Product Name", child.child("Product Name").getValue());
                    product.put("Product Description", child.child("Product Name").getValue());
                    product.put("Product Price", child.child("Product Price").getValue());
                    //product.put("Product Image", child.child())
                    products.put((String)product.get("Product Name"), product);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return products;

    }

    public ArrayList<String> getStores() {
        DatabaseReference newreference = reference.child("Stores");
        ArrayList<String> storenames = new ArrayList<>();
        newreference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {
                    storenames.add((String) child.child("Store Name").getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return storenames;
    }

    public List<PreviousOrder> getPreviousOrders(String email) {
        return PreviousOrder.getPreviousOrders(reference.child("Stores").child("Email").child("Previous Orders"), email);
    }

}
