package com.example.b07group7project;

import com.example.b07group7project.database_abstractions.Store;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    HashMap<Product, Integer> productsuuidToQuantity;
    Store store;
    Customer customer;

    String uuid;



    public Order(HashMap<Product, Integer> productsuuidToQuantity, Store store, Customer customer) {
        this.productsuuidToQuantity= productsuuidToQuantity;
        this.store = store;
        this.customer = customer;
        uuid = UUID.randomUUID().toString();
    }

    public HashMap<String, Object> putIntoHashMap() {
        HashMap<String, Object> newhashmap = new HashMap<>();
        newhashmap.put(Constants.customer_uuid, customer.uuid);
        newhashmap.put(Constants.order_complete, "false");
        HashMap<String, Integer> products = convertHashMap();
        newhashmap.put(Constants.store_products, products);
        return newhashmap;
    }

    private HashMap<String, Integer> convertHashMap() {
        HashMap<String, Integer> newhashmap = new HashMap<>();
        for (Map.Entry<Product, Integer> m : productsuuidToQuantity.entrySet()) {
            newhashmap.put(m.getKey().uuid, m.getValue());
        }
        return newhashmap;
    }

    public Store getStore(){
        return store;
    }

    public String getUuid() {
        return uuid;
    }
}
