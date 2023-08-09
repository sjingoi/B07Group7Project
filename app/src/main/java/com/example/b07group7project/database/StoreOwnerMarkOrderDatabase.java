package com.example.b07group7project.database;



import com.example.b07group7project.Product;

import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.mark_order.StoreOrder;
import com.example.b07group7project.shopper_view_previous_orders.OrderStatus;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseReference;


import java.util.ArrayList;


public class StoreOwnerMarkOrderDatabase extends Database{
    ArrayList<StoreOrder> orders;
    public StoreOwnerMarkOrderDatabase (){super();}

    public void getStoreOwnerOrders(User user, OnComplete<ArrayList<StoreOrder>> onComplete) {
        DatabaseReference newreference = root.child(Constants.store_orders).child(user.uuid);
        get(newreference, snapshot -> {
            ArrayList<StoreOrder> storeOrders = new ArrayList<>();
            for (DataSnapshot storeOrder: snapshot.getChildren()) {
                ArrayList<OrderedProduct> products = new ArrayList<>();
                for (DataSnapshot product: storeOrder.child(Constants.store_products).getChildren()) {
                    String productName = (String) product.child(Constants.product_name).getValue();
                    String productUUID = (String) product.child(Constants.product_uuid).getValue();
                    String productDescription = (String) product.child(Constants.product_description).getValue();
                    String imageURL = (String) product.child(Constants.product_image).getValue();
                    int price = (Integer) product.child(Constants.product_price).getValue();
                    StoreProduct product1 = new StoreProduct(productName, productDescription,imageURL, price, productUUID);
                    int quantity = (Integer) product.child(Constants.quantity).getValue();
                    String orderStatus = (String) storeOrder.child(Constants.order_status).getValue();
                    String customerUUID = (String) storeOrder.child(Constants.customer_uuid).getValue();
                    OrderedProduct orderedProduct = new OrderedProduct(product1, orderStatus, quantity, customerUUID);
                    products.add(orderedProduct);
                }
                StoreOrder storeOrder1 = new StoreOrder(products, storeOrder.getKey());
                storeOrders.add(storeOrder1);
            }
            onComplete.onComplete(storeOrders);
        });
    }

    public void markOrderAsComplete(User user, OrderedProduct orderedProduct, StoreOrder storeOrder)  {
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.getStoreUUID(user, uuid -> onReceiveStoreUUID(orderedProduct, storeOrder, uuid));
    }

    public void onReceiveStoreUUID(OrderedProduct orderedProduct, StoreOrder storeOrder, String storeUUID) {
        DatabaseReference reference1 = root.child(Constants.store_orders).child(storeUUID).child(storeOrder.getCurrentDate()).child(Constants.order_status);
        put(reference1, snapshot -> OrderStatus.ORDER_COMPLETE.toString());
        DatabaseReference reference2 = root.child(Constants.customers).child(storeOrder.getCustomerUUID()).child(Constants.previous_orders).child(Constants.current_date).child(orderedProduct.getProduct().getStoreProductUUID());
        put(reference2, snapshot -> OrderStatus.ORDER_COMPLETE.toString());
    }

}
