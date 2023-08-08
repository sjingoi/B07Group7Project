package com.example.b07group7project;

import com.example.b07group7project.database_abstractions.Store;

import java.util.HashMap;
import java.util.UUID;

public class Order {
    HashMap<Product, Integer> productUUIDToQuantity;
    Store store;
    Customer customer;

    String uuid;



    public Order(HashMap<Product, Integer> productUUIDToQuantity, Store store, Customer customer) {
        this.productUUIDToQuantity = productUUIDToQuantity;
        this.store = store;
        this.customer = customer;
        uuid = UUID.randomUUID().toString();
    }

    public Store getStore(){
        return store;
    }

    public String getUuid() {
        return uuid;
    }
}
