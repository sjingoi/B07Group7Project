package com.example.b07group7project.database_abstractions;

public class StoreProduct {
    String itemName;
    String description;
    String imageURL;
    String uuid;
    double price;

    public StoreProduct(String itemName, String description, String imageURL, double price) {
        this.itemName = itemName;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

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

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUUID(){
        return uuid;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
