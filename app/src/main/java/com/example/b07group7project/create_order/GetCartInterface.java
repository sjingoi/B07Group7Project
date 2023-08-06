package com.example.b07group7project.create_order;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.shopping_cart.CartEntry;

import java.util.ArrayList;

public interface GetCartInterface {

    void getCart(OnComplete<ArrayList<CartEntry>> onComplete);
}
