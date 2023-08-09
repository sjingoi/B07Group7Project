package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.itempreview.GetProductInfo;
import com.example.b07group7project.view_products.GetProductsInterface;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class StoreProductDatabase extends Database implements GetProductsInterface, GetProductInfo {

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


    // Code For Getting Individual Product
    @Override
    public void getProductFromFirebase(String storeUUID, String itemUUID, OnComplete<StoreProduct> withProduct) {
        if(storeUUID == null){
            withProduct.onComplete(new StoreProduct());
            return;
        }

        get(
                root.child(Constants.products).child(storeUUID),
                snapshot -> {
                    StoreProduct Product = extractProduct(storeUUID, itemUUID, snapshot);
                    withProduct.onComplete(Product);
                }
        );
    }


    private StoreProduct extractProduct(String storeUUID, String itemUUID, DataSnapshot snapshot){
        StoreProduct product = new StoreProduct();
        if(!snapshot.exists())
            return product;
        DataSnapshot s = snapshot.child(itemUUID);


        String uuid = s.getKey();


        String description = s.child(Constants.product_description).getValue(String.class);
        String imageURL = s.child(Constants.product_image).getValue(String.class);
        String name = s.child(Constants.product_name).getValue(String.class);
        Double price = s.child(Constants.product_price).getValue(Double.class);

        product = new StoreProduct(name, uuid, storeUUID, description, imageURL, price);







        return product;
    }

}
