package com.example.b07group7project.shopper_view_previous_orders;

import com.example.b07group7project.database_abstractions.StoreProduct;

public class OrderedProduct {
    StoreProduct product;
    OrderStatus orderStatus;
    long quantity;

    String shopperUUID;

    String date;
    String orderUUID;

    public OrderedProduct(StoreProduct product, OrderStatus orderStatus, long quantity, String shopperUUID, String date, String orderUUID) {
        this.product = product;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.shopperUUID = shopperUUID;
        this.date = date;
        this.orderUUID = orderUUID;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public StoreProduct getProduct() {
        return product;
    }

    //public int getQuantity() {
    //    return quantity;
    //}

    public String getShopperUUID() {
        return shopperUUID;
    }
}
