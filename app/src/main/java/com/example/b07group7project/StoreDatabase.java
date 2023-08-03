package com.example.b07group7project;

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
import java.util.Set;
import java.util.UUID;

public class StoreDatabase {
    FirebaseDatabase database;
    DatabaseReference reference;

    HashMap<Object, >

    public StoreDatabase(){
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
    }


    public void addStore(Store store){
        DatabaseReference newreference = reference.child("Stores").child(store.uuid);

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
        DatabaseReference newreference = reference.child("Stores").child(store.uuid).child("Products").child(store.uuid);

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

    public void addOrderToStore(HashMap<String, Integer> productsuuidToQuantity, String storeuuid, String customeruuid) {
        UUID uuid = UUID.randomUUID();
        DatabaseReference newreference = reference.child("Stores").child(storeuuid).child("Orders").child(uuid.toString());
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    newreference.setValue(productsuuidToQuantity);
                    newreference.child("Customer ID").setValue(customeruuid);
                    newreference.child("Order Complete").setValue(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }



    public void getProductsForStore(HashMap<String, Object> products, String uuid) {
        DatabaseReference newreference = reference.child("Stores").child(uuid).child("Products");
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, Object> product = new HashMap<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    product.put("Product Description", child.child("Product Description").getValue());
                    product.put("Product Price", child.child("Product Price").getValue());
                    product.put("Product Image", child.child("Product Image").getValue());
                    products.put((String)product.get("Product Name"), product);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void getStores(ArrayList<String> storenames) {
        DatabaseReference newreference = reference.child("Stores");
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
    }

    public List<PreviousOrder> getPreviousOrders(String email) {
        return PreviousOrder.getPreviousOrders(reference.child("Stores").child("Email").child("Previous Orders"), email);
    }

}
