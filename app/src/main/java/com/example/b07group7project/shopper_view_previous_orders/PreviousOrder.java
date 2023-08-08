package com.example.b07group7project.shopper_view_previous_orders;


import java.util.List;

public class PreviousOrder {
    String previousOrderLabel;
    OrderStatus orderStatus;
    List<OrderedProduct> orderedProducts;

    public PreviousOrder(String previousOrderLabel, OrderStatus orderStatus, List<OrderedProduct> orderedProducts) {
        this.previousOrderLabel = previousOrderLabel;
        this.orderStatus = orderStatus;
        this.orderedProducts = orderedProducts;
    }

    public PreviousOrder(String previousOrderLabel, OrderStatus orderStatus) {
        this.previousOrderLabel = previousOrderLabel;
        this.orderStatus = orderStatus;
    }

    public String getPreviousOrderLabel() {
        return previousOrderLabel;
    }

    public OrderStatus getOrderstatus() {
        return orderStatus;
    }

    public boolean isOrderComplete
}
