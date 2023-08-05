package com.example.b07group7project.itempreview;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;

public class StoreItemService implements GetItemInfo{
    // Method to get item information asynchronously
    public CompletableFuture<StoreItem> getItemInformation(String itemID) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("store_items/" + itemID);
        CompletableFuture<StoreItem> result = new CompletableFuture<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StoreItem item = dataSnapshot.getValue(StoreItem.class);
                if (item != null) {
                    result.complete(item);
                } else {
                    // If no item found, complete the future with a new empty StoreItem
                    result.complete(new StoreItem());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // If there was an error, complete the future with a new empty StoreItem
                result.complete(new StoreItem());
            }
        });

        return result;
    }
}

