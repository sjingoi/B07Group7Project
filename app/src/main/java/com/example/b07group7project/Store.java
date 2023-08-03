package com.example.b07group7project;

public class Store{
    // This Is Store Class for Testing, Will be changed later when Creating real store class
    String storeName;
    // image can be changed to appropiate type
    String image;

    public Store() {
        // Required empty public constructor
    }

    public Store(String storeName, String image) {
        this.storeName = storeName;
        this.image = image;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getImage() {
        return image;
    }
}