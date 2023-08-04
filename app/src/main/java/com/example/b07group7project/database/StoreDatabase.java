package com.example.b07group7project.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b07group7project.GetStoreInterface;
import com.example.b07group7project.Store;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreDatabase implements GetStoreInterface {
    ArrayList<Store> stores;
    FirebaseDatabase database;
    DatabaseReference reference;


    public StoreDatabase(){
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
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
        HashMap<String, Object> o = (HashMap<String, Object>) dataSnapshot.getValue();
        Log.d("db", "updateStoreListDataSnap: " + dataSnapshot);
        ArrayList<Store> store = new ArrayList<>();
        for (Map.Entry<String, Object> e : o.entrySet()) {
            Log.d("myLog", e.getKey());
            String name = null;
            Object nameObject = ((HashMap<String, Object>)e.getValue()).getOrDefault("Store Name", null);
            if(nameObject != null)
                name = nameObject.toString();
            String url = null;
            Object urlObject = ((HashMap<String, Object>)e.getValue()).getOrDefault("Store Image", null);
            if(urlObject != null)
                url = urlObject.toString();

            store.add(new Store(name, url));
        }
        Log.d("myLog", "storeList: " + store.size());
        stores = store;
    }
}
