package com.example.b07group7project.database;

import com.example.b07group7project.Order;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class StoreOwnerMarkOrderDatabase extends Database{

    public StoreOwnerMarkOrderDatabase (){super();}

    public void getStoreOwnerOrders(OnComplete<ArrayList<Order>> onComplete) {
        DatabaseReference newreference = root.child(Constants.store_orders);
        get()
    }
}
