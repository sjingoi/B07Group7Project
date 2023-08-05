package com.example.b07group7project;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Order {
    HashMap<Object, String> productsuuidToQuantity;
    Store store;
    Customer customer;

    String uuid;

    public Order() {

    }

    public Order(HashMap<Object, String> productsuuidToQuantity, Store store, Customer customer) {
        this.productsuuidToQuantity= productsuuidToQuantity;
        this.store = store;
        this.customer = customer;
        uuid = UUID.randomUUID().toString();
    }


}
