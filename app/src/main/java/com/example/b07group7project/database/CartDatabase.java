package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopping_cart.CartEntry;
import com.example.b07group7project.shopping_cart.GetCartEntries;
import com.example.b07group7project.shopping_cart.StoreHeader;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDatabase extends Database implements GetCartEntries {

    static List<CartEntry> cart;
    @Override
    public void getCartEntries(OnComplete<List<CartEntry>> onComplete) {
        AccountDatabase db = new AccountDatabase();

        db.getUserUUID(
                User.getCurrentUser(),
                uuid -> getCartEntriesWithUUID(uuid, onComplete)
        );
    }

    private void getCartEntriesWithUUID(String uuid, OnComplete<List<CartEntry>> onComplete) {
        if(cart != null){
            onComplete.onComplete(new ArrayList<>(cart));
            return;
        }

        getWithCache(
                root.child(Constants.customers).child(uuid).child(Constants.shopping_cart),
                onComplete,
                this::loadCart
        );

    }
    //TODO: (Optional) should we remove invalid entries automatically?
    private List<CartEntry> loadCart(DataSnapshot dataSnapshot) {
        ArrayList<CartEntry> entries = new ArrayList<>();
        for (DataSnapshot item: dataSnapshot.getChildren()) {
            String[] UUIDs = Objects.requireNonNull(item.getKey()).split("\\s+");
            DataSnapshot values = item.getValue(DataSnapshot.class);
            if(values == null)
                continue;
            String itemName = values.child(Constants.product_name).getValue(String.class);
            String description = values.child(Constants.product_description).getValue(String.class);
            String imageURL = values.child(Constants.product_image).getValue(String.class);
            Double price = values.child(Constants.product_price).getValue(Double.class);
            if(price == null)
                continue;
            StoreProduct product = new StoreProduct(itemName, description, imageURL, price);
            product.setUUID(UUIDs[1]);

            String storeName = values.child(Constants.store_name).getValue(String.class);
            StoreHeader storeHeader = new StoreHeader(storeName, UUIDs[0]);

            Integer quantity = values.child(Constants.quantity).getValue(Integer.class);
            if(quantity == null)
                continue;

            CartEntry e = new CartEntry(product, storeHeader, quantity);
            entries.add(e);
        }
        cart = entries;
        return entries;
    }
}
