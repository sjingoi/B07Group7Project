package com.example.b07group7project.shopper_view_previous_orders;

import java.util.ArrayList;

public class getPreviousOrderImplementation implements getPreviousOrders{


    @Override
    public ArrayList<PreviousOrder> getPreviousOrders() {
        ArrayList<PreviousOrder> previousOrders = new ArrayList<>();
        PreviousOrder previousOrder1 = new PreviousOrder("isaiah", "Order Complete");
        PreviousOrder previousOrder2 = new PreviousOrder("sebastian", "Order Incomplete");
        previousOrders.add(previousOrder1);
        previousOrders.add(previousOrder2);
        return previousOrders;
    }
}
