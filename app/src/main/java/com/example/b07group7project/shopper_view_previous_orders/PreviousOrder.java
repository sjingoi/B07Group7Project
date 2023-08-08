package com.example.b07group7project.shopper_view_previous_orders;


import java.util.List;

public class PreviousOrder {
    String previousOrderLabel;
    OrderStatus orderStatus;
    List<OrderedProduct> orderedProducts;

    public PreviousOrder(String previousOrderLabel,  List<OrderedProduct> orderedProducts) {
        this.previousOrderLabel = previousOrderLabel;
        this.orderedProducts = orderedProducts;
        this.orderStatus = isOrderComplete();
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

    public OrderStatus isOrderComplete() {
        if (orderedProducts == null) {
            return OrderStatus.ORDER_INCOMPLETE;
        }
        for (OrderedProduct orderedProduct : orderedProducts) {
            if (orderedProduct.orderStatus == OrderStatus.ORDER_INCOMPLETE) {
                return OrderStatus.ORDER_INCOMPLETE;
            }
        }
        return OrderStatus.ORDER_COMPLETE;
    }
}
