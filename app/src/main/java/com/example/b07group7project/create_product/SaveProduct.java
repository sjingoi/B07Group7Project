package com.example.b07group7project.create_product;

public interface SaveProduct {
    void saveProductToFirebase(String itemName, String itemDesc, String itemURL, String storeID, double itemPrice);
}
