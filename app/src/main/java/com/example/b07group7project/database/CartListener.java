package com.example.b07group7project.database;

public interface CartListener {
    public void addToCart(String storeuuid, String customeruuid, String itemID, int quantity);
}