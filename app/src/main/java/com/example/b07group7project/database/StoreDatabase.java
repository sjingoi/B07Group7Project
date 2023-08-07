package com.example.b07group7project.database;

import static com.example.b07group7project.database.Constants.DATABASE_URL;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b07group7project.Order;
import com.example.b07group7project.Product;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.shopper_view_store.GetStoreInterface;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreDatabase implements GetStoreInterface {
    ArrayList<Store> stores;
    FirebaseDatabase database;
    DatabaseReference reference;


    public StoreDatabase(){
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        reference = database.getReference();
    }
    public void getStores(OnComplete<ArrayList<Store>> onComplete) {
        if(stores != null){
            onComplete.onComplete(new ArrayList<>(stores));
            return;
        }

        DatabaseReference databaseReference = reference.child("Stores");
        databaseReference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateStoreList(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("db", error.toString());
            }
        }));

        Task<DataSnapshot> dataSnapshotTask = databaseReference.get();
        dataSnapshotTask.addOnCompleteListener(v -> {
            updateStoreList(v.getResult());
            onComplete.onComplete(new ArrayList<>(stores));
        });
    }

    public void updateStoreList(DataSnapshot dataSnapshot){
        Log.d("db", "updateStoreListDataSnap: " + dataSnapshot);
        ArrayList<Store> store = new ArrayList<>();
        for (DataSnapshot e : dataSnapshot.getChildren()) {
            Log.d("myLog", e.getKey());
            HashMap<String, Object> value = (HashMap<String, Object>) e.getValue();
            if(value == null)
                continue;
            String name = getObjectAsString(
                    value.getOrDefault("Store Name", null)
            );
            String url = getObjectAsString(
                    value.getOrDefault("Store Image", null)
            );

            store.add(new Store(name, url));
        }
        Log.d("myLog", "storeList: " + store.size());
        stores = store;
    }

    private String getObjectAsString(Object o){
        if(o == null)
            return null;
        return o.toString();
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
        DatabaseReference newreference = reference.child(Constants.stores).child(store.getUuid()).child(Constants.products)
                .child(product.getUuid());

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
        DatabaseReference newreference = reference.child(Constants.stores).child(order.getStore().getUuid())
                .child(Constants.orders).child(order.getUuid());

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
}
