package com.example.b07group7project.view_products;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;

public interface GetProductsInterface {

    void getProducts(String storeID, OnComplete<ArrayList<StoreProduct>> withProductList);
}
