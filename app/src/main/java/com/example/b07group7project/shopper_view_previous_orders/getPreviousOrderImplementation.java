package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database.OnComplete;

import java.util.ArrayList;
import java.util.Arrays;

public class getPreviousOrderImplementation implements getPreviousOrdersInterface{
    @Override
    public void getPreviousOrders(OnComplete<ArrayList<PreviousOrder>> onComplete) {
        PreviousOrder[] previousOrders = {
                new PreviousOrder("isaiah", OrderStatus.ORDER_COMPLETE),
                new PreviousOrder("sebastian", OrderStatus.ORDER_INCOMPLETE)
        };
        onComplete.onComplete(new ArrayList<>(Arrays.asList(previousOrders)));
    }
}
