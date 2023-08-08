package com.example.b07group7project.shopper_view_previous_orders;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

public class PreviousOrder {
    //String previousOrderLabel;
    OrderStatus orderStatus;
    List<OrderedProduct> orderedProducts;

    String currentDate;

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public PreviousOrder(List<OrderedProduct> orderedProducts) {
        //this.previousOrderLabel = previousOrderLabel;
        this.orderedProducts = orderedProducts;
        this.orderStatus = isOrderComplete();
        currentDate = LocalDateTime.now().toString();
    }

    public PreviousOrder(List<OrderedProduct> orderedProducts, String currentDate) {
        this.orderedProducts = orderedProducts;
        this.orderStatus = isOrderComplete();
        this.currentDate = currentDate;
    }

    public PreviousOrder(OrderStatus orderStatus) {
        //this.previousOrderLabel = previousOrderLabel;
        this.orderStatus = orderStatus;
        currentDate = LocalDateTime.now().toString();
        currentDate = currentDate.substring(0, currentDate.indexOf("T")) + " at " + currentDate.substring(currentDate.indexOf("T") + 1, currentDate.indexOf("."));
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
            if (orderedProduct.orderStatus.equals(OrderStatus.ORDER_INCOMPLETE)) {
                return OrderStatus.ORDER_INCOMPLETE;
            }
        }
        return OrderStatus.ORDER_COMPLETE;
    }
}
