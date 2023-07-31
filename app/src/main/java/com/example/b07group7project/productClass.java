package com.example.b07group7project;

public class productClass {
    private String itemDesc;
    private String storeID;
    private String itemName;
    private String itemID;

    public String getItemDesc() {
        return itemDesc;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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

    public String getItemID() {
        return itemID;
    }

    public productClass(String itemDesc, String storeID, String itemName, String itemID) {
        this.itemDesc = itemDesc;
        this.storeID = storeID;
        this.itemName = itemName;
        this.itemID = itemID;
    }
}
