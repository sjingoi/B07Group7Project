package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database_abstractions.StoreProduct;

public class OrderedProduct {
    StoreProduct product;
    String orderStatus;
    long quantity;

    String shopperUUID;

    String date;
    String orderUUID;

    public OrderedProduct(StoreProduct product, String orderStatus, long quantity, String shopperUUID, String date, String orderUUID) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.shopperUUID = shopperUUID;
        this.date = date;
        this.orderUUID = orderUUID;
    }
}
