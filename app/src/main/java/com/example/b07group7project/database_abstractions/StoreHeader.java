package com.example.b07group7project.database_abstractions;

public class StoreHeader {
    String storeUUID;
    String storeName;

    public StoreHeader(String storeName, String storeUUID){
        this.storeName = storeName;
        this.storeUUID = storeUUID;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public String getStoreUUID() {
        return storeUUID;
    }
}
