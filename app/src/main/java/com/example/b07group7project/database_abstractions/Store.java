package com.example.b07group7project;

public class Store{
    // This Is Store Class for Testing, Will be changed later when Creating real store class
    String storeName;
    String imageURL;

    public Store() {
        // Required empty public constructor
    }

    public Store(String storeName, String imageURL) {
        this.storeName = storeName;
        this.imageURL = imageURL;
    }

    public Store(String storename, String storedescription, String email, String storeicon) {
        this.storeName = storename;
        this.storedescription = storedescription;
        this.email = email;
        productlist = new HashMap<>();
        previousOrders = new HashMap<>();
        this.imageURL = storeicon;
        this.uuid = UUID.randomUUID().toString();
    }

    public Store(String storename, String storedescription, String email, HashMap<String, Object> productlist, HashMap<String, Object> previousOrders, String storeicon) {
        this.storeName = storename;
        this.storedescription = storedescription;
        this.email = email;
        this.productlist = productlist;
        this.previousOrders = previousOrders;
        this.imageURL = storeicon;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getStoreName() {
        return storeName;
    }

    public String getImage() {
        return imageURL;
    }

}