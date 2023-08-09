package com.example.b07group7project.database;

public interface CartListener {
    void addToCart(String storeuuid, String customeruuid, String itemID, int quantity);

    void removeFromCart(String storeuuid, String customeruuid, String itemID);
}