package com.example.b07group7project.database;

public interface SaveProduct {
    void saveProductToFirebase(String itemName, String itemDesc, String itemURL, String storeID, double itemPrice);
}
