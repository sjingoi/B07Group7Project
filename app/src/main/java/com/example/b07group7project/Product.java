package com.example.b07group7project;

import java.util.HashMap;
import java.util.UUID;
import com.example.b07group7project.database_abstractions.Store;

public class Product {

    String productname;
    String description;
    String price;
    String imageURL;
    String uuid;
    String storeuuid;

    int quantity;

    public Product(String productname, String description, String price, String imageURL, Store store){
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.uuid = UUID.randomUUID().toString();
        this.storeuuid = store.getUuid();
    }
    public Product(String productname, String description, double price, String imageURL, Store store){
        this.productname = productname;
        this.description = description;
        this.price = Double.toString(price);
        this.imageURL = imageURL;
        this.uuid = UUID.randomUUID().toString();
        this.storeuuid = store.getUuid();
    }

    public Product(String productname, String description, String price, String imageURL, Store store, int quantity){
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.uuid = UUID.randomUUID().toString();
        this.storeuuid = store.getUuid();
        this.quantity = quantity;
    }
    public String getProductname() {
        return productname;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public HashMap<String, Object> putIntoHashMap(){
        HashMap<String, Object> product = new HashMap<>();
        product.put(Constants.product_name, productname);
        product.put(Constants.product_description, description);
        product.put(Constants.product_price, price);
        product.put(Constants.product_image, imageURL);
        return product;
    }
}
