package com.example.b07group7project.shopper_view_previous_orders;


import java.util.List;

public class PreviousOrder {
    //String previousOrderLabel;
    OrderStatus orderStatus;
    List<OrderedProduct> orderedProducts;

    String currentDate;

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public PreviousOrder(List<OrderedProduct> orderedProducts, String currentDate) {
        this.orderedProducts = orderedProducts;
        this.orderStatus = isOrderComplete();
        this.currentDate = currentDate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    //public String getPreviousOrderLabel() {return previousOrderLabel;}

    public OrderStatus getOrderstatus() {
        return orderStatus;
    }

    public OrderStatus isOrderComplete() {
        if (orderedProducts == null) {
            return OrderStatus.ORDER_INCOMPLETE;
        }
        for (OrderedProduct orderedProduct : orderedProducts) {
            if (orderedProduct.orderStatus != OrderStatus.ORDER_COMPLETE) {
                return OrderStatus.ORDER_INCOMPLETE;
            }
        }
        return OrderStatus.ORDER_COMPLETE;
    }
}
