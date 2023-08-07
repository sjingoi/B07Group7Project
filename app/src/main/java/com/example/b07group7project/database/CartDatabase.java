package com.example.b07group7project.database;

import android.util.Log;

import com.example.b07group7project.create_order.GetCartInterface;
import com.example.b07group7project.create_order.PlaceOrderInterface;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopping_cart.CartEntry;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDatabase extends Database implements GetCartInterface, PlaceOrderInterface {


    ArrayList<CartEntry> products;

    public CartDatabase(){
        super();
    }
    @Override
    public void getCart(OnComplete<ArrayList<CartEntry>> onComplete) {
        if(products != null){
            onComplete.onComplete(new ArrayList<>(products));
            return;
        }

        getWithCache(
                root.child(Constants.customers)
                        .child(User.getCurrentUser().uuid)
                        .child(Constants.shopping_cart),
                onComplete,
                this::updateProductList
        );
    }

    private ArrayList<CartEntry> updateProductList(DataSnapshot dataSnapshot) {
        Log.d("db", "updateStoreListDataSnap: " + dataSnapshot);
        ArrayList<CartEntry> tempProducts = new ArrayList<>();
        for (DataSnapshot e : dataSnapshot.getChildren()) {
            Log.d("myLog", e.getKey());
            HashMap<String, Object> value = (HashMap<String, Object>) e.getValue();
            if(value == null)
                continue;
            HashMap<String, Object> product = (HashMap<String, Object>) (value.get(Constants.products));
            if(product == null)
                continue;
            String name = getObjectAsString(
                    product.getOrDefault(Constants.product_name, null)
            );
            String productImage = getObjectAsString(
                    product.getOrDefault(Constants.product_image, null)
            );
            String description = getObjectAsString(
                    product.getOrDefault(Constants.product_description, null)
            );
            double price = (double) product.get(Constants.product_price);

            String store =  getObjectAsString(value.get(Constants.store_name));
            int quantity = (int) value.get(Constants.quantity);


            tempProducts.add(new CartEntry(new StoreProduct(name, description, productImage, price), store, quantity));
        }
        Log.d("myLog", "storeList: " + tempProducts.size());
        products = new ArrayList<>(tempProducts);
        return tempProducts;
    }

    @Override
    public void placeOrder(List<CartEntry> products, User user) {

    }
}
