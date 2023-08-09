package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.Product;

public class OrderedProduct {
    Product product;
    String orderStatus;
    int quantity;

    public OrderedProduct(Product product, OrderStatus orderStatus, int quantity) {
        this.product = product;
        this.orderStatus = orderStatus.toString();
        this.quantity = quantity;
    }

    public OrderedProduct(Product product, String orderStatus, int quantity) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Product getProduct() {
        return product;
    }
}
