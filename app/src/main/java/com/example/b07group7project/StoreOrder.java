package com.example.b07group7project;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StoreOrder {
    HashMap<String, Integer> productsuuidToQuantity;
    Store store;
    Customer customer;

    String uuid;

    public StoreOrder() {

    }

    public StoreOrder(HashMap<String, Integer> productsuuidToQuantity, Store store, Customer customer) {
        this.productsuuidToQuantity= productsuuidToQuantity;
        this.store = store;
        this.customer = customer;
        uuid = UUID.randomUUID().toString();
    }
}
