package com.example.b07group7project.database_abstractions;

import java.util.HashMap;
import java.util.UUID;

public class Store{
    // This Is Store Class for Testing, Will be changed later when Creating real store class
    String storeName;
    String imageURL;
    String storedescription;
    String email;

    public String getImageURL() {
        return imageURL;
    }

    public String getEmail() {
        return email;
    }

    public String getUuid() {
        return uuid;
    }

    String uuid;

    public Store() {
        // Required empty public constructor
    }

    public Store(String storeName, String imageURL) {
        this.storeName = storeName;
        this.imageURL = imageURL;
    }

    public Store(String storename, String storedescription, String email, String imageURL) {
        this.storeName = storename;
        this.storedescription = storedescription;
        this.email = email;
        this.imageURL = imageURL;
        this.uuid = UUID.randomUUID().toString();
    }


    public String getStoreName() {
        return storeName;
    }

    public String getImage() {
        return imageURL;
    }

    public HashMap<String, Object> storeIntoHashmap () {
        HashMap<String, Object> store = new HashMap<>();
        store.put("Store name", storeName);
        store.put("Store Description", storedescription);
        store.put("Email", email);
        store.put("Store Icon", imageURL);
        return store;
    }

}