package com.example.b07group7project.database;

import android.util.Log;

import com.example.b07group7project.database_abstractions.StoreHeader;
import com.example.b07group7project.shopper_view_store.GetStoreInterface;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class StoreDatabase extends Database implements GetStoreInterface {
    static ArrayList<StoreHeader> stores;


    public StoreDatabase(){
        super();
    }
    public void getStores(OnComplete<ArrayList<StoreHeader>> onComplete) {
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

    public ArrayList<StoreHeader> updateStoreList(DataSnapshot dataSnapshot){
        Log.d("db", "updateStoreListDataSnap: " + dataSnapshot);
        ArrayList<StoreHeader> store = new ArrayList<>();
        for (DataSnapshot e : dataSnapshot.getChildren()) {
            Log.d("myLog", e.getKey());
            DataSnapshot value = e.getValue(DataSnapshot.class);
            if(value == null)
                continue;
            String name = value.child(Constants.store_name).getValue(String.class);
            String url = value.child(Constants.store_image).getValue(String.class);
            String uuid = e.getKey();

            store.add(new StoreHeader(name, url, uuid));
        }
        Log.d("myLog", "storeList: " + store.size());
        stores = new ArrayList<>(store);
        return store;
    }
}
