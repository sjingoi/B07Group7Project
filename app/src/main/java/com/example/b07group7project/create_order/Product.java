package com.example.b07group7project.create_order;

// Product Class, Can Add/Remove Fields in the future as needed
public class Product {
    String description;
    String storeName;
    String itemName;
    String imageURL;
    int quantity;
    double price;



    public Product(String itemName, String description, double price, int quantity, String storeName, String imageURL) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.storeName = storeName;
        this.imageURL = imageURL;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
