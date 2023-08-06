package com.example.b07group7project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerActivity extends AppCompatActivity {
    private List<Order> pendingOrdersList = new ArrayList<>();

    // Initialize RecyclerView, Adapter, and LayoutManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner);

        // Assuming RecyclerView, Adapter, and LayoutManager set up.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Fetch pending orders from the database or your data source.
        // Forexample,  create dummy data.
        pendingOrdersList = getPendingOrders();

        // Set the pendingOrdersList to the RecyclerView Adapter
        OrderAdapter orderAdapter = new OrderAdapter(pendingOrdersList);
        recyclerView.setAdapter(orderAdapter);
    }

    private List<Order> getPendingOrders() {
        // fetch the list of pending orders from data source.
        // someone else is dealing with the database, assume  this method.
        // For now, return some dummy data for testing.

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1", "Cust001", "Product A", 5, "Pending"));
        orders.add(new Order("2", "Cust002", "Product B", 3, "Pending"));
        orders.add(new Order("3", "Cust001", "Product C", 2, "Pending"));
        // Add more orders as needed.
        return orders;
    }
}