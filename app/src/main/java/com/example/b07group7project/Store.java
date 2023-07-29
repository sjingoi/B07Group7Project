package com.example.b07group7project;

public class Store{
    // This Is Store Class for Testing, Will be changed later when Creating real store class
    String storeName;
    String storeBio;
    int image;

    public Store() {
        // Required empty public constructor
    }

    public Store(String storeName, String storeBio, int image) {
        this.storeName = storeName;
        this.storeBio = storeBio;
        this.image = image;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreBio() {
        return storeBio;
    }

    public int getImage() {
        return image;
    }
}