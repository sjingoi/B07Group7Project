package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.view_products.GetProductsInterface;

import java.util.ArrayList;

public class StoreProductDatabase extends Database implements GetProductsInterface {
    @Override
    public void getProducts(Store store, OnComplete<ArrayList<StoreProduct>> withProductList) {

    }
}
