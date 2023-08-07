package com.example.b07group7project.database;

import android.util.Log;

import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.shopper_view_store.GetStoreInterface;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreDatabase extends Database implements GetStoreInterface {
    ArrayList<Store> stores;

    public StoreDatabase(){
        super();
    }
    public void getStores(OnComplete<ArrayList<Store>> onComplete) {
        if(stores != null){
            onComplete.onComplete(new ArrayList<>(stores));
            return;
        }

        getWithCache(
                root.child("Stores"),
                onComplete,
                this::updateStoreList
        );
    }

    public ArrayList<Store> updateStoreList(DataSnapshot dataSnapshot){
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
        stores = new ArrayList<>(store);
        return store;
    }


}
