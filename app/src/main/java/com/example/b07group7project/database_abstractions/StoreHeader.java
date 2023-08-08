package com.example.b07group7project.database_abstractions;

public class StoreHeader {
    String imageURL;
    String storeUUID;
    String storeName;

    public StoreHeader(String storeName, String storeUUID){
        this.storeName = storeName;
        this.storeUUID = storeUUID;
    }

    public StoreHeader(String storeName, String storeUUID, String imageURL){
        this.storeName = storeName;
        this.storeUUID = storeUUID;
        this.imageURL = imageURL;
    }

    public String getImage() {
        return this.imageURL;
    }

    public String getStoreName() {
        return this.storeName;
    }
}
