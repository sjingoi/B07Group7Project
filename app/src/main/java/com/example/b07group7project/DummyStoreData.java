package com.example.b07group7project;

import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopper_view_previous_orders.OrderStatus;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;

import java.util.ArrayList;
import java.util.List;

public class DummyStoreData implements OrderInterface{

    @Override
    public void getPendingOrdersForStoreOwner(String storeOwnerId, OrderCallback callback) {
        List<OrderedProduct> dummyOrders = new ArrayList<>();
        /*
        OrderedProduct order1 = new OrderedProduct(new StoreProduct("Product 1", "product1 Id", "Item 1", "Description for Product 1", "random", 20), OrderStatus.ORDER_INCOMPLETE, 23, "Random ID shopper");
        OrderedProduct order2 = new OrderedProduct(new StoreProduct("Product 2", "product2 Id", "Item 2", "Description for Product 2", "randomss", 3), OrderStatus.ORDER_INCOMPLETE, 2, "Random ID shopperss");

        dummyOrders.add(order1);
        dummyOrders.add(order2);

        callback.onPendingOrdersLoaded(dummyOrders);
        */
    }

    @Override
    public void confirmOrder(OrderedProduct order, OrderCallback callback) {

    }
}
