package com.example.b07group7project.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Database {
    FirebaseDatabase database;
    DatabaseReference root;

    public Database(){
        database = FirebaseDatabase.getInstance();
        root = database.getReference();
    }

    <T> void getWithCache(DatabaseReference ref, OnComplete<T> a, DataLoader<T> b){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                b.load(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("db", error.toString());
            }
        });
        Task<DataSnapshot> dataSnapshotTask = ref.get();
        dataSnapshotTask.addOnCompleteListener(v -> {
            T x = b.load(v.getResult());
            a.onComplete(x);
        });

    }

    void get(DatabaseReference ref, DataExtractor a){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                a.extract(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    <T> void put(DatabaseReference ref, DataSetter<T> a){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                T toSet = a.getData(snapshot);
                ref.setValue(toSet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    String getObjectAsString(Object o){
        if(o == null)
            return null;
        return o.toString();
    }
}
