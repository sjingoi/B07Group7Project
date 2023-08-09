package com.example.b07group7project;

import com.example.b07group7project.database_abstractions.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyOrder implements OrderInterface {

    @Override
    public void getPendingOrdersForStoreOwner(String storeOwnerId, OrderCallback callback) {
        // assume  a list of pending orders for the store owner with the given ID.
        //  query the actual database to get the pending orders.
        // testing,  create some dummy orders.

        List<Order> dummyOrders = new ArrayList<>();

        // Assume  list of orders for the store owner with the given ID
        // For testing, adding two dummy orders.

        // Dummy Order 1
        HashMap<Product, Integer> products1 = new HashMap<>();
        products1.put(new Product("ProductA", "Description A", "10.99", "image_url_A", new Store("Store1", "img", "Store1 UUID"), 5), 1);
        products1.put(new Product("ProductB", "Description B", "5.99", "image_url_B", new Store("Store1", "img", "Store1 UUID"), 3), 2);
        Order order1 = new Order(products1, new Store("Store1", "img", "Store1 UUID"), new Customer("Customer1", "Customer1 UUID"));
        dummyOrders.add(order1);

        // Dummy Order 2
        HashMap<Product, Integer> products2 = new HashMap<>();
        products2.put(new Product("ProductC", "Description C", "7.99", "image_url_C", new Store("Store1", "img", "Store1 UUID"), 2), 3);
        Order order2 = new Order(products2, new Store("Store1", "img", "Store1 UUID"), new Customer("Customer2", "Customer2 UUID"));
        dummyOrders.add(order2);

        callback.onPendingOrdersLoaded(dummyOrders);
    }

    @Override
    public void confirmOrder(Order order, OrderCallback callback) {
        //  update the status of the order to "Confirmed" in the database.
        // Since  dummy data, just assume the order is confirmed. do later
        //callback.onOrderConfirmationSuccess();
    }
}
