package com.example.b07group7project;

import android.app.DownloadManager;

import androidx.annotation.NonNull;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.shopper_view_store.GetStoreInterface;
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


public class StoreDatabase implements GetStoreInterface {
    FirebaseDatabase database;
    DatabaseReference reference;

    List<com.example.b07group7project.database_abstractions.Store> stores;

    public StoreDatabase(){
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }


    public void addStore(com.example.b07group7project.database_abstractions.Store store){
        DatabaseReference newreference = reference.child(Constants.stores).child(store.getUuid());

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

    public void addProductToStore(Product product, com.example.b07group7project.database_abstractions.Store store){
        DatabaseReference newreference = reference.child(Constants.stores).child(store.getUuid()).child(Constants.products).child(product.uuid);

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
        DatabaseReference newreference = reference.child(Constants.stores).child(order.store.getUuid()).child(Constants.orders).child(order.uuid);

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


    /*
    @Override
    public List<com.example.b07group7project.Store> getStores() {
        return null;
    }
    */
    @Override
    public void getStores(OnComplete<ArrayList<Store>> withStoreList) {

    }
}
