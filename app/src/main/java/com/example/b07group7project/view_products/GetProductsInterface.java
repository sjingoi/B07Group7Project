package com.example.b07group7project.view_products;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;

// TODO: Implement Interface through the database
public interface GetProductsInterface {

    void getProducts(OnComplete<ArrayList<StoreProduct>> withProductList);
}