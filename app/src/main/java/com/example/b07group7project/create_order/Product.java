package com.example.b07group7project.create_order;

// Product Class, Can Add/Remove Fields in the future as needed
public class Product {
    String description;
    int price;
    String imageURL;
    int quantity;
    String storeName;

    public Product(String description, int price, String imageURL, int quantity, String storeName) {
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.quantity = quantity;
        this.storeName = storeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
