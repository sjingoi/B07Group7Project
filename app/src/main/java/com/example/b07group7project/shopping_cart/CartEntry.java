package com.example.b07group7project.shopping_cart;

import com.example.b07group7project.database_abstractions.StoreHeader;
import com.example.b07group7project.database_abstractions.StoreProduct;

public class CartEntry {

    StoreProduct product;
    StoreHeader store;
    int quantity;


    public CartEntry(StoreProduct product, StoreHeader store, int quantity) {
        this.product = product;
        this.store = store;
        this.quantity = quantity;
    }

    public CartEntry() {

    }

    public StoreProduct getProduct() {
        return product;
    }

    public void setProduct(StoreProduct product) {
        this.product = product;
    }

    public StoreHeader getStore() {
        return store;
    }

    public void setStore(StoreHeader store) {
        this.store = store;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
