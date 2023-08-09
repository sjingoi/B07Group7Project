package com.example.b07group7project.database_abstractions;

import java.io.Serializable;

public class StoreProduct implements Serializable {

    String productID;

    String storeID;
    String itemName;
    String description;
    String imageURL;
    double price;


    public StoreProduct(String itemName, String productID, String storeID, String description, String imageURL, double price) {
        this.itemName = itemName;
        this.productID = productID;
        this.storeID = storeID;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

    public StoreProduct(){}

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }
}
