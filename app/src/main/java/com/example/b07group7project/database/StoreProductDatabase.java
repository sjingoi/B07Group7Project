package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.view_products.GetProductsInterface;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class StoreProductDatabase extends Database implements GetProductsInterface {

    public StoreProductDatabase(){
        super();
    }
    @Override
    public void getProducts(String storeUUID, OnComplete<ArrayList<StoreProduct>> withProductList) {
        if(storeUUID == null){
            withProductList.onComplete(new ArrayList<>());
            return;
        }

        get(
                root.child(Constants.products).child(storeUUID),
                snapshot -> {
                    ArrayList<StoreProduct> products = extractProductList(storeUUID, snapshot);
                    withProductList.onComplete(products);
                }
        );
    }

    private ArrayList<StoreProduct> extractProductList(String storeUUID, DataSnapshot snapshot){
        ArrayList<StoreProduct> products = new ArrayList<>();
        if(!snapshot.exists())
            return products;
        for (DataSnapshot s: snapshot.getChildren()) {
            String uuid = s.getKey();

            String description = s.child(Constants.product_description).getValue(String.class);
            String imageURL = s.child(Constants.product_image).getValue(String.class);
            String name = s.child(Constants.product_name).getValue(String.class);
            Double price = s.child(Constants.product_price).getValue(Double.class);
            if(price == null)
                continue;

            StoreProduct product = new StoreProduct(name, uuid, storeUUID, description, imageURL, price);

            products.add(product);
        }

        return products;
    }
}
