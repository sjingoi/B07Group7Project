package com.example.b07group7project.shopping_cart;
import android.view.View;

import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database_abstractions.StoreProduct;

public class CartEntry {

    StoreProduct product;
    Store store;
    int quantity;

    View.OnClickListener onClickListener;

    public CartEntry(StoreProduct product, Store store, int quantity) {
        this.product = product;
        this.store = store;
        this.quantity = quantity;
    }

    public CartEntry(StoreProduct product, Store store, int quantity, View.OnClickListener onClick) {
        this.product = product;
        this.store = store;
        this.quantity = quantity;
        this.onClickListener = onClick;
    }

    public StoreProduct getProduct() {
        return product;
    }

    public void setProduct(StoreProduct product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
