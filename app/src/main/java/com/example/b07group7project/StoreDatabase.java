package com.example.b07group7project;

import android.app.DownloadManager;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StoreDatabase implements GetStoreInterface{
    FirebaseDatabase database;
    DatabaseReference reference;

    List<Store> stores;

    public StoreDatabase(){
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }


    public void addStore(Store store){
        DatabaseReference newreference = reference.child(Constants.stores).child(store.uuid);

        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    HashMap<String, Object> storemap = store.storeIntoHashmap();
                    newreference.setValue(storemap);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void addProductToStore(Product product, Store store){
        DatabaseReference newreference = reference.child(Constants.stores).child(store.uuid).child(Constants.products).child(product.uuid);

        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    HashMap<String, Object> finalproduct = product.putIntoHashMap();
                    newreference.setValue(finalproduct);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addOrderToStore(Order order) {
        DatabaseReference newreference = reference.child(Constants.stores).child(order.store.uuid).child(Constants.orders).child(order.uuid);

        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    HashMap<String, Object> hashmap = order.putIntoHashMap();
                    newreference.setValue(hashmap);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    @Override
    public List<Store> getStores() {
        return null;
    }
}
