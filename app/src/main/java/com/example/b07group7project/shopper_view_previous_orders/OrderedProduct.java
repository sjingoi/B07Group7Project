package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.Product;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.UUID;

public class OrderedProduct {
    StoreProduct product;
    String orderStatus;
    int quantity;
    String customerUUID;
    String orderUUID;
    String currentDate;

    public OrderedProduct(StoreProduct product, String orderStatus, int quantity, String customerUUID, String orderUUID) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.customerUUID = customerUUID;
        this.orderUUID = orderUUID;
    }

    public OrderedProduct(StoreProduct product, OrderStatus orderStatus, int quantity) {
        this.product = product;
        this.orderStatus = orderStatus.toString();
        this.quantity = quantity;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getCustomerUUID() {
        return customerUUID;
    }

    public OrderedProduct(StoreProduct product, String orderStatus, int quantity) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public StoreProduct getProduct() {
        return product;
    }
}
