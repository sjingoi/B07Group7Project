package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.view_products.GetProductsInterface;

import java.util.ArrayList;

public class StoreProductDatabase implements GetProductsInterface {
    @Override
    public void getProducts(OnComplete<ArrayList<StoreProduct>> withProductList) {

    }
}
