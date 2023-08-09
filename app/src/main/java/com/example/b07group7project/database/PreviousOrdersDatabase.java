package com.example.b07group7project.database;

import androidx.annotation.NonNull;


import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;
import com.example.b07group7project.shopper_view_previous_orders.PreviousOrder;
import com.example.b07group7project.shopper_view_previous_orders.getPreviousOrdersInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreviousOrdersDatabase extends Database implements getPreviousOrdersInterface {

    ArrayList<PreviousOrder> previousOrders;
    //User user;

    public PreviousOrdersDatabase() {super();}
    @Override
    public void getPreviousOrders(User user, OnComplete<ArrayList<PreviousOrder>> onComplete) {
        if (previousOrders != null) {
            onComplete.onComplete(new ArrayList<>(previousOrders));
            return;
        }
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.getUserUUID(user, userUUID -> onReceiveUserUUID(onComplete, userUUID));
    }

    public void onReceiveUserUUID(OnComplete<ArrayList<PreviousOrder>> onComplete, String userUUID) {
        DatabaseReference newreference = root.child(Constants.customers).child(userUUID).child(Constants.previous_orders);
        get(newreference, snapshot -> {
            ArrayList<PreviousOrder> previousOrderList = new ArrayList<>();
            for (DataSnapshot previousOrder : snapshot.child("PreviousOrders").getChildren()) {
                ArrayList<OrderedProduct> orderedProductList = new ArrayList<>();
                for (DataSnapshot product : previousOrder.getChildren()) {
                    String productName = (String) product.child(Constants.product_name).getValue();
                    String productDescription = (String) product.child(Constants.product_description).getValue();
                    String imageURL = (String) product.child(Constants.product_image).getValue();
                    String productUUID = (String) product.child(Constants.product_uuid).getValue();
                    int productPrice = (Integer) product.child(Constants.product_price).getValue();
                    StoreProduct storeProduct = new StoreProduct(productName, productDescription, imageURL, productPrice, productUUID);
                    int quantity = (Integer) product.child(Constants.quantity).getValue();
                    String orderStatus = (String) product.child(Constants.order_status).getValue();
                    String customerUUID = (String) product.child(Constants.customer_UUID).getValue();
                    OrderedProduct orderedProduct = new OrderedProduct(storeProduct, orderStatus, quantity, customerUUID);
                    orderedProductList.add(orderedProduct);
                }
                String currentDate = (String) previousOrder.getKey();
                PreviousOrder exPreviousOrder = new PreviousOrder(orderedProductList, currentDate);
                previousOrderList.add(exPreviousOrder);
            }
            onComplete.onComplete(previousOrderList);
        });
    }
}
