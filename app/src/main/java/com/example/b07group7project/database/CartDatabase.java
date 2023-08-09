package com.example.b07group7project.database;

import androidx.annotation.NonNull;

import com.example.b07group7project.create_order.PlaceOrderInterface;
import com.example.b07group7project.database_abstractions.StoreHeader;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopper_view_previous_orders.OrderStatus;
import com.example.b07group7project.shopping_cart.CartEntry;
import com.example.b07group7project.shopping_cart.GetCartEntries;
import com.google.firebase.database.DataSnapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CartDatabase extends Database implements GetCartEntries, PlaceOrderInterface {

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

    @Override
    public void placeOrder(List<CartEntry> products, User user) {
        AccountDatabase db = new AccountDatabase();
        db.getUserUUID(user,
                userUUID -> placeOrderWithUUID(products, userUUID)
        );
    }

    private void placeOrderWithUUID(List<CartEntry> products, String userUUID) {
        String date = LocalDateTime.now().toString().replace(".", "?").replace(":", ";");
        for (CartEntry e: products) {
            String orderUUID = UUID.randomUUID().toString();
            StoreHeader header = e.getStore();

            get(
                    root.child(Constants.products).child(header.getStoreUUID()).child(e.getProduct().getProductID()),
                    productSnapshot -> {
                        addProductToStoreOrderList(userUUID, e, header, productSnapshot, orderUUID, date);
                        addProductToCustomerPreviousOrderList(userUUID, e, header, productSnapshot, orderUUID, date);
                        removeProductFromShoppingCart(userUUID, e);
                    }
            );
        }
    }

    private void removeProductFromShoppingCart(String userUUID, CartEntry e) {
        String key = e.getStore().getStoreUUID() + ":" + e.getProduct().getProductID();
        delete(
                root.child(Constants.customers).child(userUUID).child(Constants.shopping_cart).child(key)
        );
    }

    private void addProductToCustomerPreviousOrderList(String userUUID, CartEntry e, StoreHeader header, DataSnapshot productSnapshot, String orderUUID, String date) {
        put(
                root.child(Constants.customers).child(userUUID).child(Constants.previous_orders).child(date).child(orderUUID),
                snapshot -> {
                    HashMap<String, Object> x = toOrderMap(userUUID, e, productSnapshot);
                    x.put(Constants.store_uuid, header.getStoreUUID());
                    x.put(Constants.store_name, header.getStoreName());
                    return x;
                }
        );
    }

    private void addProductToStoreOrderList(String userUUID, CartEntry e, StoreHeader header, DataSnapshot productSnapshot, String orderUUID, String date) {
        String key = date + ":" + orderUUID;



        put(
                root.child(Constants.store_orders).child(header.getStoreUUID()).child(key),
                snapshot -> toOrderMap(userUUID, e, productSnapshot)
        );
    }

    @NonNull
    private static HashMap<String, Object> toOrderMap(String userUUID, CartEntry e, DataSnapshot productSnapshot) {
        String description = productSnapshot.child(Constants.product_description).getValue(String.class);
        String imageURL = productSnapshot.child(Constants.product_image).getValue(String.class);
        String name = productSnapshot.child(Constants.product_name).getValue(String.class);
        Double price = productSnapshot.child(Constants.product_price).getValue(Double.class);

        if(price == null)
            return new HashMap<>();

        HashMap<String, Object> product = new HashMap<>();
        product.put(Constants.product_name, name);
        product.put(Constants.product_description, description);
        product.put(Constants.product_image, imageURL);
        product.put(Constants.product_price, price);
        product.put(Constants.product_uuid, productSnapshot.getKey());
        product.put(Constants.quantity, e.getQuantity());
        product.put(Constants.order_status, OrderStatus.ORDER_INCOMPLETE);
        product.put(Constants.customer_uuid, userUUID);
        return product;
    }
}

