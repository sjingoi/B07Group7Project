package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.Product;

public class OrderedProduct {
    String productUUID;
    String orderStatus;
    int quantity;

    public OrderedProduct(String productUUID, OrderStatus orderStatus, int quantity) {
        this.productUUID = productUUID;
        this.orderStatus = orderStatus.toString();
        this.quantity = quantity;
    }
    public OrderedProduct(String productUUID, String orderStatus, int quantity) {
        this.productUUID = productUUID;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
    }

    public String getProductUUID() {
        return productUUID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getQuantity() {
        return quantity;
    }
}
