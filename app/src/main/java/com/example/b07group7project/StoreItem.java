package com.example.b07group7project;

public class StoreItem {
    private String itemDesc;
    private String storeID;
    private String itemName;
    private String itemID;
    private String itemPrice;

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

    public StoreItem(String itemDesc, String storeID, String itemName, String itemID, String itemPrice) {
        this.itemDesc = itemDesc;
        this.storeID = storeID;
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
    }
}
