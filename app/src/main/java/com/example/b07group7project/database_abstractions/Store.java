package com.example.b07group7project.database_abstractions;

public class Store{
    // This Is Store Class for Testing, Will be changed later when Creating real store class

    String storeName;
    String uuid;
    String storeicon;
    String imageURL;
    String storedescription;
    String email;



//    public Store() {
//        // Required empty public constructor
//    }
//
//    public Store(String storeName, String imageURL) {
//        this.storeName = storeName;
//        this.imageURL = imageURL;
//    }
//
//    public Store(String storename, String storedescription, String email, String storeicon) {
//        this.storeName = storename;
//        this.storedescription = storedescription;
//        this.email = email;
//        this.imageURL = storeicon;
//        this.uuid = UUID.randomUUID().toString();
//    }

    public Store(String storeName, String uuid, String storeIcon) {
        this.storeName = storeName;
        this.uuid = uuid;
        this.imageURL = storeIcon;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getImage() {
        return imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getUuid() {
        return uuid;
    }

    public String getStoreicon() {
        return storeicon;
    }



//    public HashMap<String, Object> storeIntoHashmap () {
//        HashMap<String, Object> store = new HashMap<>();
//        store.put("Store name", storeName);
//        store.put("Store Description", storedescription);
//        store.put("Email", email);
//        store.put("Store Icon", storeicon);
//        return store;
//    }

}