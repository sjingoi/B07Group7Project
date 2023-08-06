package com.example.b07group7project.database_abstractions;

public class Store{
    // This Is Store Class for Testing, Will be changed later when Creating real store class
    String storeName;
    String imageURL;

    public Store(String storeName, String imageURL) {
        this.storeName = storeName;
        this.imageURL = imageURL;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getImage() {
        return imageURL;
    }
}