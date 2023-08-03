package com.example.b07group7project.itempreview;

public class GetItemInfoImplementation implements GetItemInfo{

    @Override
    public StoreItem getItemInformation() {
        // Replace this with actual implementation to retrieve the item information.
        // For demonstration purposes, I create a sample StoreItem object here.
        return new StoreItem("Sample Item Description", "store123", "Sample Item", "item123", 9.99);
    }
}
