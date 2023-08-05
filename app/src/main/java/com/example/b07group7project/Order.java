package com.example.b07group7project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Order {
    HashMap<Product, String> productsuuidToQuantity;
    Store store;
    Customer customer;

    String uuid;

    public Order() {

    }

    public Order(HashMap<Product, String> productsuuidToQuantity, Store store, Customer customer) {
        this.productsuuidToQuantity= productsuuidToQuantity;
        this.store = store;
        this.customer = customer;
        uuid = UUID.randomUUID().toString();
    }

    public HashMap<String, Object> putIntoHashMap() {
        HashMap<String, Object> newhashmap = new HashMap<>();
        newhashmap.put(Constants.customer_uuid, customer.uuid);
        newhashmap.put(Constants.order_complete, "false");
        HashMap<String, String> products = convertHashMap();
        newhashmap.put(Constants.store_products, products);
        return newhashmap;
    }

    private HashMap<String, String> convertHashMap() {
        HashMap<String, String> newhashmap = new HashMap<>();
        for (Map.Entry<Product, String> m : productsuuidToQuantity.entrySet()) {
            newhashmap.put(m.getKey().uuid, m.getValue());
        }
        return newhashmap;
    }
}
