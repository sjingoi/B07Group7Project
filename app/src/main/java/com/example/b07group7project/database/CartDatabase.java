package com.example.b07group7project.database;

import com.example.b07group7project.create_order.PlaceOrderInterface;
import com.example.b07group7project.database_abstractions.StoreHeader;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopper_view_previous_orders.OrderStatus;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;
import com.example.b07group7project.shopping_cart.CartEntry;
import com.example.b07group7project.shopping_cart.GetCartEntries;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

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
            String storeUUID = item.child(Constants.store_uuid).getValue(String.class);
            String productUUID = item.child(Constants.product_uuid).getValue(String.class);
            String quantity = item.child(Constants.quantity).getValue(String.class);
            toFetch.add(new String[]{storeUUID, productUUID, quantity});
            entries.add(new CartEntry());
        }

        for (DataSnapshot item: productSnapshot.getChildren()) {
            String[] matchingIdSet = new String[0];
            String storeUUID = item.getKey();
            boolean hasItem = false;
            for (String[] idSet: toFetch) {
                if(idSet[0].equals(storeUUID)){
                    hasItem = true;
                    break;
                }
            }
            if(!hasItem)
                continue;

            DataSnapshot values = item.child(Constants.product_uuid);
            String productUUID = values.getKey();
            hasItem = false;
            for (String[] idSet: toFetch) {
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
            StoreProduct product = new StoreProduct(itemName, description, imageURL, price);

            String storeName = values.child(Constants.store_name).getValue(String.class);
            StoreHeader storeHeader = new StoreHeader(storeName, storeUUID);
            

            CartEntry e = new CartEntry(product, storeHeader, Integer.parseInt(matchingIdSet[2]));
            entries.add(e);
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
        for (CartEntry e: products) {
            StoreHeader header = e.getStore();

            get(
                    root.child(Constants.products).child(header.getStoreUUID()).child(e.getProduct().getUUID()),
                    productSnapshot -> addProductToOrderList(userUUID, e, header, productSnapshot)
            );
        }
    }

    private void addProductToOrderList(String userUUID, CartEntry e, StoreHeader header, DataSnapshot productSnapshot) {
        String description = productSnapshot.child(Constants.product_description).getValue(String.class);
        String imageURL = productSnapshot.child(Constants.product_image).getValue(String.class);
        String name = productSnapshot.child(Constants.product_name).getValue(String.class);
        Double price = productSnapshot.child(Constants.product_price).getValue(Double.class);

        if(price == null)
            return;

        StoreProduct p = new StoreProduct(name, description, imageURL, price);
        put(
                root.child(Constants.store_orders).child(header.getStoreUUID()),
                snapshot -> {
                    ArrayList list = snapshot.getValue(ArrayList.class);
                    if(list == null)
                        list = new ArrayList<OrderedProduct>();
                    list.add(new OrderedProduct(p, OrderStatus.ORDER_INCOMPLETE, e.getQuantity(), userUUID));
                    return list;
                }
        );
    }
}

