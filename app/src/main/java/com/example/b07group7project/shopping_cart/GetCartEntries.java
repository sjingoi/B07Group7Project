package com.example.b07group7project.shopping_cart;

import com.example.b07group7project.database.OnComplete;

import java.util.ArrayList;

public interface GetCartEntries {
    void getCartEntries(OnComplete<ArrayList<CartEntry>> onComplete);
}
