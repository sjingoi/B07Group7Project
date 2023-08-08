package com.example.b07group7project.database;

import androidx.annotation.NonNull;

import com.example.b07group7project.Product;
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
        DatabaseReference newreference = root.child(Constants.customers).child(User.getCurrentUser().uuid).child(Constants.previous_orders);
        get(newreference, snapshot -> {
            ArrayList<PreviousOrder> previousOrderList = new ArrayList<>();
            for (DataSnapshot previousOrder : snapshot.child("PreviousOrders").getChildren()) {
                ArrayList<OrderedProduct> orderedProductList = new ArrayList<>();
                for (DataSnapshot orderedProduct : previousOrder.child(Constants.ordered_products).getChildren()) {
                    String productUUID = (String) orderedProduct.getValue();
                    int quantity = (Integer) snapshot.child(Constants.quantity).getValue();
                    String orderStatus = (String) snapshot.child(Constants.order_status).getValue();
                    OrderedProduct actualOrderedProduct = new OrderedProduct(productUUID, orderStatus, quantity);
                    orderedProductList.add(actualOrderedProduct);
                }
                String currentDate = (String) previousOrder.getValue();
                PreviousOrder exPreviousOrder = new PreviousOrder(orderedProductList, currentDate);
                previousOrderList.add(exPreviousOrder);
                onComplete.onComplete(previousOrderList);
            }
        });
    }




    public void addPreviousOrder(PreviousOrder previousOrder) {
        DatabaseReference newreference =root.child(Constants.customers).child(User.getCurrentUser().uuid).child(Constants.previous_orders).child(previousOrder.getCurrentDate());

        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    HashMap<String, Object> hashmap = new HashMap<>();
                    for (OrderedProduct orderedProduct : previousOrder.getOrderedProducts()){
                        HashMap<String, String> productInfo = new HashMap<>();
                        productInfo.put(Constants.order_status, orderedProduct.getOrderStatus().toString());
                        productInfo.put(Constants.quantity, Integer.toString(orderedProduct.getQuantity()));
                        hashmap.put(orderedProduct.getProductUUID(), hashmap);
                    }
                    newreference.setValue(hashmap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
