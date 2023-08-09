package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database_abstractions.StoreProduct;

public class OrderedProduct {
    StoreProduct product;
    OrderStatus orderStatus;
    int quantity;

    String shopperUUID;

    public OrderedProduct(StoreProduct product, OrderStatus orderStatus, int quantity, String shopperUUID) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.shopperUUID = shopperUUID;
    }
}
