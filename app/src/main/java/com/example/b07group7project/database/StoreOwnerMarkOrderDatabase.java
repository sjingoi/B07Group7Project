package com.example.b07group7project.database;

import com.example.b07group7project.Order;
import com.example.b07group7project.Product;
import com.example.b07group7project.StoreOrder;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;

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
                    String imageURL = (String) product.child(Constants.product_image).getValue();
                    int quantity = (Integer) product.child(Constants.quantity).getValue();
                    Product product1 = new Product(productName, imageURL, quantity);
                    String orderStatus = (String) storeOrder.child(Constants.order_status).getValue();
                    OrderedProduct orderedProduct = new OrderedProduct(product1, orderStatus.toString(), product1.getQuantity());
                    products.add(orderedProduct);
                }
                StoreOrder storeOrder1 = new StoreOrder(products, storeOrder.getKey());
                storeOrders.add(storeOrder1);
            }
            onComplete.onComplete(storeOrders);
        });
    }
}
