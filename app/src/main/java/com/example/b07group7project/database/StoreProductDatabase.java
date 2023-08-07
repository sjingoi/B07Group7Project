package com.example.b07group7project.database;

import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.view_products.GetProductsInterface;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreProductDatabase extends Database implements GetProductsInterface {
    @Override
    public void getProducts(Store store, OnComplete<ArrayList<StoreProduct>> withProductList) {
        String storeUUID = store.getUuid();
        get(
                root.child(Constants.products).child(storeUUID),
                snapshot -> {
                    ArrayList<StoreProduct> out = toStoreProductList(snapshot);
                    withProductList.onComplete(out);
                }
        );
    }

    private ArrayList<StoreProduct> toStoreProductList(DataSnapshot snapshot){
        if(snapshot == null)
            return new ArrayList<>();

        ArrayList<StoreProduct> products = new ArrayList<>();
        for (DataSnapshot entry : snapshot.getChildren()){
            String uuid = entry.getKey();
            HashMap<String, Object> value = (HashMap<String, Object>) entry.getValue(HashMap.class);
            if(value == null)
                continue;
            String description = (String) value.get(Constants.product_description);
            String image = (String) value.get(Constants.product_image);
            String name = (String) value.get(Constants.product_name);
            double price = Double.parseDouble((String) value.get(Constants.product_price));
            products.add(new StoreProduct(name, description, image, uuid, price));
        }

        return products;
    }
}
