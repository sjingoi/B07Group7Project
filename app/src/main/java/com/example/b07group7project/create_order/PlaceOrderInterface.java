package com.example.b07group7project.create_order;

import com.example.b07group7project.shopping_cart.CartEntry;

import java.util.List;

public interface PlaceOrderInterface {
    // TODO: Change User From String to Appropriate Type
    public void placeOrder(List<CartEntry> products, String user);
}
