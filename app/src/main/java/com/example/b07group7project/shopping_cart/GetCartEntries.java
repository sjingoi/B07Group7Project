package com.example.b07group7project.shopping_cart;

import com.example.b07group7project.database.OnComplete;

import java.util.List;

public interface GetCartEntries {
    void getCartEntries(OnComplete<List<CartEntry>> onComplete);
}
