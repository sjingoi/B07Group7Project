package com.example.b07group7project;

import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;

import java.util.List;

public interface OrderInterface {
    interface OrderCallback {
        void onPendingOrdersLoaded(List<OrderedProduct> orders);

    }

    void getPendingOrdersForStoreOwner(String storeOwnerId, OrderCallback callback);
    void confirmOrder(OrderedProduct order, OrderCallback callback);
}
