package com.example.b07group7project.mark_order;

import com.example.b07group7project.shopper_view_previous_orders.OrderStatus;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;

import java.time.LocalDateTime;
import java.util.List;

public class StoreOrder {

    OrderStatus orderStatus;
    List<OrderedProduct> orderedProducts;
    String currentDate;

    public StoreOrder(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
        this.orderStatus = isOrderComplete();
        currentDate = LocalDateTime.now().toString();
    }

    public StoreOrder(List<OrderedProduct> orderedProducts, String currentDate) {
        this.orderedProducts = orderedProducts;
        this.orderStatus = isOrderComplete();
        this.currentDate = currentDate;
    }

    public StoreOrder(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        currentDate = LocalDateTime.now().toString();
        currentDate = currentDate.substring(0, currentDate.indexOf("T")) + " at " + currentDate.substring(currentDate.indexOf("T") + 1, currentDate.indexOf("."));
    }

    public OrderStatus isOrderComplete() {
        if (orderedProducts == null) {
            return OrderStatus.ORDER_INCOMPLETE;
        }
        for (OrderedProduct orderedProduct : orderedProducts) {
            if (orderedProduct.getOrderStatus().equals(OrderStatus.ORDER_INCOMPLETE)) {
                return OrderStatus.ORDER_INCOMPLETE;
            }
        }
        return OrderStatus.ORDER_COMPLETE;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
