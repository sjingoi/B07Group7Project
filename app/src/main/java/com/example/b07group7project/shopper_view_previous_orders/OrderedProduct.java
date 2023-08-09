package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.Product;
import com.example.b07group7project.database_abstractions.StoreProduct;

public class OrderedProduct {
    String productUUID;
    StoreProduct storeProduct;
    String orderStatus;
    int quantity;
    String customerUUID;

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

    public OrderedProduct(StoreProduct storeProduct, String orderStatus, int quantity) {
        this.storeProduct = storeProduct;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
    }

    public OrderedProduct(StoreProduct storeProduct, String orderStatus, int quantity, String customerUUID) {
        this.storeProduct = storeProduct;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.customerUUID = customerUUID;
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
