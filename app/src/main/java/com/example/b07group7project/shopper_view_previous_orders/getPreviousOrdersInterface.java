package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database.User;

import java.util.ArrayList;

public interface getPreviousOrdersInterface {
    public void getPreviousOrders(User user, OnComplete<ArrayList<PreviousOrder>> onComplete);

}
