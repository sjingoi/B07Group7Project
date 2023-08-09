package com.example.b07group7project.shopping_cart;

import com.example.b07group7project.database_abstractions.StoreProduct;

public class CartEntry {

    StoreProduct product;
    String storeID;
    int quantity;

    public CartEntry(StoreProduct product, String store, int quantity) {
        this.product = product;
        this.storeID = store;
        this.quantity = quantity;
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

}
