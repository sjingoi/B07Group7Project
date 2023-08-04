package com.example.b07group7project.create_order;

// Product Class, Can Add/Remove Fields in the future as needed
public class Product {


    String itemName;
    int quantity;
    double price;



    public Product(String itemName, double price, int quantity) {
        this.itemName = itemName;

        this.price = price;
        this.quantity = quantity;



    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
