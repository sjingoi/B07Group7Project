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
        DatabaseReference newreference = root.child(Constants.store_orders).child(user.uuid);
        get(newreference, snapshot -> {
            ArrayList<OrderedProduct> storeOrders = new ArrayList<>();
            for (DataSnapshot storeOrder: snapshot.getChildren()) {
                String productName = (String) storeOrder.child(Constants.product_name).getValue();
                String productUUID = (String) storeOrder.child(Constants.product_uuid).getValue();
                String productDescription = (String) storeOrder.child(Constants.product_description).getValue();
                String imageURL = (String) storeOrder.child(Constants.product_image).getValue();
                String storeUUID = (String) storeOrder.child(Constants.store_uuid).getValue();
                int price = (Integer) storeOrder.child(Constants.product_price).getValue();
                StoreProduct product1 = new StoreProduct(productName, productUUID, storeUUID, productDescription, imageURL, price);
                //StoreProduct product1 = new StoreProduct(productName, productDescription,imageURL, price, productUUID);
                int quantity = (Integer) storeOrder.child(Constants.quantity).getValue();
                String orderStatus = (String) storeOrder.child(Constants.order_status).getValue();
                String customerUUID = (String) storeOrder.child(Constants.customer_uuid).getValue();
                String orderUUID = (String) storeOrder.getKey().split(":")[1];
                OrderedProduct orderedProduct = new OrderedProduct(product1, orderStatus, quantity, customerUUID, orderUUID);
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
        DatabaseReference reference1 = root.child(Constants.store_orders).child(storeUUID).child(orderedProduct.getCurrentDate()).child(Constants.order_status);
        put(reference1, snapshot -> OrderStatus.ORDER_COMPLETE.toString());
        DatabaseReference reference2 = root.child(Constants.customers).child(orderedProduct.getCustomerUUID()).
                child(Constants.previous_orders).child(Constants.current_date).child(orderedProduct.getProduct().getProductID());
        put(reference2, snapshot -> OrderStatus.ORDER_COMPLETE.toString());
    }

}
