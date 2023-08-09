package com.example.b07group7project.create_order;

import com.example.b07group7project.database.User;
import com.example.b07group7project.shopping_cart.CartEntry;

import java.util.List;

public interface PlaceOrderInterface {
    void placeOrder(List<CartEntry> products, User user);
}
