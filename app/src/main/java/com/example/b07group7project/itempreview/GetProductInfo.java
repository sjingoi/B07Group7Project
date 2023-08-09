package com.example.b07group7project.itempreview;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;

public interface GetProductInfo {
    public void getProductFromFirebase(String storeID, String itemID, OnComplete<StoreProduct> withProductList);
}
