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
        get(
                root.child(Constants.products).child(Constants.store_uuid),
                snapshot -> {
                    ArrayList<StoreProduct> products = extractProductList(snapshot);
                    withProductList.onComplete(products);
                }
        );
    }

    private ArrayList<StoreProduct> extractProductList(DataSnapshot snapshot){
        ArrayList<StoreProduct> products = new ArrayList<>();

        for (DataSnapshot s: snapshot.getChildren()) {
            String uuid = s.getKey();
            DataSnapshot child = s.getValue(DataSnapshot.class);
            if(child == null)
                continue;

            String description = s.child(Constants.product_description).getValue(String.class);
            String imageURL = s.child(Constants.product_image).getValue(String.class);
            String name = s.child(Constants.product_name).getValue(String.class);
            Double price = s.child(Constants.product_price).getValue(Double.class);
            if(price == null)
                continue;

            StoreProduct product = new StoreProduct(name, description, imageURL, price, uuid);

            products.add(product);
        }

        return products;
    }
}
