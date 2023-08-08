package com.example.b07group7project.shopping_cart;
import android.view.View;

import com.example.b07group7project.database_abstractions.StoreProduct;

public class CartEntry {

    StoreProduct product;
    String storeID;
    int quantity;

    View.OnClickListener onClickListener;

    public CartEntry(StoreProduct product, String store, int quantity) {
        this.product = product;
        this.storeID = store;
        this.quantity = quantity;
    }

    public CartEntry(StoreProduct product, String storeID, int quantity, View.OnClickListener onClick) {
        this.product = product;
        this.storeID = storeID;
        this.quantity = quantity;
        this.onClickListener = onClick;
    }

    public StoreProduct getProduct() {
        return product;
    }

    public void setProduct(StoreProduct product) {
        this.product = product;
    }

    public String getStore() {
        return storeID;
    }

    public void setStore(String store) {
        this.storeID = store;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
