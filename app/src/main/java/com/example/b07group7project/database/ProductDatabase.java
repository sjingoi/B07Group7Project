package com.example.b07group7project.database;

import static com.example.b07group7project.database.Constants.DATABASE_URL;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b07group7project.create_order.GetCartInterface;
import com.example.b07group7project.create_order.Product;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDatabase implements GetCartInterface {


    ArrayList<Product> products;
    FirebaseDatabase database;
    DatabaseReference reference;


    public ProductDatabase(){
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        reference = database.getReference();
    }
    @Override
    public void getCart(OnComplete<ArrayList<Product>> onComplete) {
        if(products != null){
            onComplete.onComplete(new ArrayList<>(products));
            return;
        }

        DatabaseReference databaseReference = reference.child("Customers")
                .child("f025806d-594f-443a-a146-d6d773274c4a")
                .child("Cart");
        databaseReference.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateProductList(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("db", error.toString());
            }
        }));

        Task<DataSnapshot> dataSnapshotTask = databaseReference.get();
        dataSnapshotTask.addOnCompleteListener(v -> {
            updateProductList(v.getResult());
            onComplete.onComplete(new ArrayList<>(products));
        });
    }

    private void updateProductList(DataSnapshot dataSnapshot) {
        Log.d("db", "updateStoreListDataSnap: " + dataSnapshot);
        ArrayList<Product> tempProducts = new ArrayList<>();
        for (DataSnapshot e : dataSnapshot.getChildren()) {
            Log.d("myLog", e.getKey());
            HashMap<String, Object> value = (HashMap<String, Object>) e.getValue();
            if(value == null)
                continue;
            String name = getObjectAsString(
                    value.getOrDefault("ProductName", null)
            );
            double price = (double) value.get("Price");
            int quantity = (int) value.get("Quantity");


            tempProducts.add(new Product(name, price, quantity));
        }
        Log.d("myLog", "storeList: " + tempProducts.size());
        products = tempProducts;
    }

    private String getObjectAsString(Object o){
        if(o == null)
            return null;
        return o.toString();
    }
}
