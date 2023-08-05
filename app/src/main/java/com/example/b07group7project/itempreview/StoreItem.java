package com.example.b07group7project.itempreview;

public class StoreItem {
    private String ProductDesc;
    private String storeID;
    private String ProductName;
    private String itemID;
    private double ProductPrice;
    private int itemQuant;

    public StoreItem(String itemID, String ProductName, int itemQuant) {
        this.itemID = itemID;
        this.ProductName = ProductName;
        this.itemQuant = itemQuant;
    }

    public String getItemDesc() {
        return ProductDesc;
    }

    public String getItemName() {
        return ProductName;
    }
    public double getItemPrice() {
        return ProductPrice;
    }


    public String getItemID() {
        return itemID;
    }

    public StoreItem(String itemDesc, String storeID, String itemName, String itemID, double itemPrice, int itemQuant) {
        this.ProductDesc = itemDesc;
        this.storeID = storeID;
        this.ProductName = itemName;
        this.itemID = itemID;
        this.ProductPrice = itemPrice;
        this.itemQuant = itemQuant;
    }
}