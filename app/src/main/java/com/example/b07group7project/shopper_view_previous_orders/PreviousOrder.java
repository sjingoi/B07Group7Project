package com.example.b07group7project.shopper_view_previous_orders;


import com.example.b07group7project.Product;

import java.util.ArrayList;

public class PreviousOrder {
    String previousorderlabel;
    String orderstatus;


    public PreviousOrder(String previousorder, String orderstatus) {
        this.previousorderlabel = previousorder;
        this.orderstatus = orderstatus;
    }

    public String getPreviousorder() {
        return previousorderlabel;
    }

    public String getOrderstatus() {
        return orderstatus;
    }
}
