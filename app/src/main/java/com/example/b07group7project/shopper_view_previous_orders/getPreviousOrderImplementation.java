package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database.OnComplete;

import java.util.ArrayList;
import java.util.Arrays;

public class getPreviousOrderImplementation implements getPreviousOrdersInterface{
    @Override
    public void getPreviousOrders(OnComplete<ArrayList<PreviousOrder>> onComplete) {
        //ArrayList<PreviousOrder> previousOrders = new ArrayList<>();
        //PreviousOrder previousOrder1 = new PreviousOrder("isaiah", "Order Complete");
        //PreviousOrder previousOrder2 = new PreviousOrder("sebastian", "Order Incomplete");
        //previousOrders.add(previousOrder1);
        //previousOrders.add(previousOrder2);
        PreviousOrder[] previousOrders = {
                new PreviousOrder("isaiah", "Order Complete"),
                new PreviousOrder("sebastian", "Order Incomplete")
        };
        onComplete.onComplete(new ArrayList<>(Arrays.asList(previousOrders)));
    }

    /*
    @Override
    public ArrayList<PreviousOrder> getPreviousOrders() {
        ArrayList<PreviousOrder> previousOrders = new ArrayList<>();
        PreviousOrder previousOrder1 = new PreviousOrder("isaiah", "Order Complete");
        PreviousOrder previousOrder2 = new PreviousOrder("sebastian", "Order Incomplete");
        previousOrders.add(previousOrder1);
        previousOrders.add(previousOrder2);
        return previousOrders;
    }

     */

}
