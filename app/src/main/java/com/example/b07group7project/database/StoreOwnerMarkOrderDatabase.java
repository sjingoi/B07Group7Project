package com.example.b07group7project.database;




import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopper_view_previous_orders.OrderStatus;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;
import java.util.List;


public class StoreOwnerMarkOrderDatabase extends Database{

    public StoreOwnerMarkOrderDatabase (){super();}

    public void getStoreOwnerOrders(User user, OnComplete<ArrayList<OrderedProduct>> onComplete) {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.getStoreUUID(user, storeUUID -> onReceiveStoreUUID(onComplete, storeUUID));

    }

    public void onReceiveStoreUUID(OnComplete<ArrayList<OrderedProduct>> onComplete, String storeUUID) {
        DatabaseReference newreference = root.child(Constants.store_orders).child(storeUUID);
        get(newreference, snapshot -> {
            ArrayList<OrderedProduct> storeOrders = new ArrayList<>();
            for (DataSnapshot storeOrder: snapshot.getChildren()) {
                String productName = (String) storeOrder.child(Constants.product_name).getValue();
                String productUUID = (String) storeOrder.child(Constants.product_uuid).getValue();
                String productDescription = (String) storeOrder.child(Constants.product_description).getValue();
                String imageURL = (String) storeOrder.child(Constants.product_image).getValue();
                Double price = storeOrder.child(Constants.product_price).getValue(Double.class);
                if (price == null){continue;}
                StoreProduct product1 = new StoreProduct(productName, productUUID, storeUUID, productDescription, imageURL, price);
                Integer quantity = storeOrder.child(Constants.quantity).getValue(Integer.class);
                if (quantity == null){continue;}
                OrderStatus orderStatus = storeOrder.child(Constants.order_status).getValue(OrderStatus.class);
                String shopperUUID = (String) storeOrder.child(Constants.customer_uuid).getValue();
                String orderUUID = (String) storeOrder.getKey().split(":")[1];
                String date = (String) storeOrder.getKey().split(":")[0];
                date = date.replace(";", ":");
                date = date.replace("?", ".");
                OrderedProduct orderedProduct = new OrderedProduct(product1, orderStatus, quantity, shopperUUID, date, orderUUID);
                storeOrders.add(orderedProduct);
            }
            onComplete.onComplete(storeOrders);
        });
    }

    public void markOrderAsComplete(User user, OrderedProduct orderedProduct)  {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.getStoreUUID(user, uuid -> onReceiveStoreUUID(orderedProduct, uuid));
    }

    public void onReceiveStoreUUID(OrderedProduct orderedProduct, String storeUUID) {
        String date = orderedProduct.getDate().replace(":", ";").replace(".", "?");
        String orderID = orderedProduct.getOrderUUID();
        String key = date + ":" + orderID;
        DatabaseReference reference1 = root.child(Constants.store_orders).child(storeUUID).child(key).child(Constants.order_status);
        put(reference1, snapshot -> OrderStatus.ORDER_COMPLETE);
        DatabaseReference reference2 = root.child(Constants.customers).child(orderedProduct.getShopperUUID()).
                child(Constants.previous_orders).child(date).child(orderedProduct.getOrderUUID()).child(Constants.order_status);
        put(reference2, snapshot -> OrderStatus.ORDER_COMPLETE);
    }

}
