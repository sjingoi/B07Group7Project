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

    public StoreItem() {
    }

    public int getItemQuant() {
        return itemQuant;
    }

    public void setItemQuant(int itemQuant) {
        this.itemQuant = itemQuant;
    }

    public String getItemDesc() {
        return ProductDesc;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getItemName() {
        return ProductName;
    }
    public double getItemPrice() {
        return ProductPrice;
    }

    public void setItemDesc(String itemDesc) {
        this.ProductDesc = itemDesc;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    public String getItemID() {
        return itemID;
    }

    public StoreItem(String itemDesc, String storeID, String itemName, String itemID, double itemPrice, int itemQuant) {
        this.itemDesc = itemDesc;
        this.storeID = storeID;
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemQuant = itemQuant;
    }
}