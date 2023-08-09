package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.StoreHeader;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopping_cart.CartEntry;
import com.example.b07group7project.shopping_cart.GetCartEntries;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class CartDatabase extends Database implements GetCartEntries {

    @Override
    public void getCartEntries(OnComplete<ArrayList<CartEntry>> onComplete) {
        AccountDatabase db = new AccountDatabase();

        db.getUserUUID(
                User.getCurrentUser(),
                uuid -> getCartEntriesWithUUID(uuid, onComplete)
        );
    }

    private void getCartEntriesWithUUID(String uuid, OnComplete<ArrayList<CartEntry>> onComplete) {

        get(
                root.child(Constants.customers).child(uuid).child(Constants.shopping_cart),
                cartSnapshot -> get(
                        root.child(Constants.products),
                        productSnapshot -> loadCart(cartSnapshot, productSnapshot, onComplete)
                )
        );

    }
    private void loadCart(DataSnapshot cartSnapshot, DataSnapshot productSnapshot, OnComplete<ArrayList<CartEntry>> onComplete) {
        ArrayList<CartEntry> entries = new ArrayList<>();
        ArrayList<String[]> toFetch = new ArrayList<>();
        for (DataSnapshot item: cartSnapshot.getChildren()){
            if(!item.exists() || item.getKey() == null)
                continue;
            String[] UUIDs = item.getKey().split(":");

            Long quantity = item.child(Constants.quantity).getValue(Long.class);
            String qString = (quantity == null)? null:quantity.toString();

            toFetch.add(new String[]{UUIDs[0], UUIDs[1], qString});
            entries.add(new CartEntry());
        }

        for (DataSnapshot storeSnapshot: productSnapshot.getChildren()) {
            String storeUUID = storeSnapshot.getKey();
            if(storeUUID == null)
                continue;

            boolean hasItem = false;
            for (String[] idSet: toFetch) {
                if(idSet[0] == null || idSet[1] == null || idSet[2] == null)
                    continue;

                if(idSet[0].equals(storeUUID)){
                    hasItem = true;
                    break;
                }
            }
            if(!hasItem)
                continue;

            for (DataSnapshot values: storeSnapshot.getChildren()){
                String[] matchingIdSet = new String[0];
                String productUUID = values.getKey();
                hasItem = false;
                for (String[] idSet: toFetch) {
                    if(idSet[0] == null || idSet[1] == null || idSet[2] == null)
                        continue;

                    if(idSet[0].equals(storeUUID) && idSet[1].equals(productUUID)){
                        hasItem = true;
                        matchingIdSet = idSet;
                        break;
                    }
                }
                if(!hasItem)
                    continue;

                String itemName = values.child(Constants.product_name).getValue(String.class);
                String description = values.child(Constants.product_description).getValue(String.class);
                String imageURL = values.child(Constants.product_image).getValue(String.class);
                Double price = values.child(Constants.product_price).getValue(Double.class);
                if(price == null)
                    continue;
                StoreProduct product = new StoreProduct(itemName, productUUID, storeUUID, description, imageURL, price);

                String storeName = values.child(Constants.store_name).getValue(String.class);
                StoreHeader storeHeader = new StoreHeader(storeName, storeUUID);


                CartEntry e = new CartEntry(product, storeHeader, Integer.parseInt(matchingIdSet[2]));
                entries.add(e);
            }
        }

        onComplete.onComplete(entries);
    }
}
