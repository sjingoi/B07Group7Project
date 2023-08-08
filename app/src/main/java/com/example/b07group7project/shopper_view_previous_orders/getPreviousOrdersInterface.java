package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database.OnComplete;

import java.util.ArrayList;

public interface getPreviousOrdersInterface {
    public void getPreviousOrders(OnComplete<ArrayList<PreviousOrder>> onComplete);

}
