package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.b07group7project.Order;
import com.example.b07group7project.OrderAdapter;
import com.example.b07group7project.R;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerActivity extends AppCompatActivity {
    private List<Order> pendingOrdersList = new ArrayList<>();

    // Initialize RecyclerView, Adapter, and LayoutManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner);

        // Assuming you have RecyclerView, Adapter, and LayoutManager set up.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Fetch pending orders from the database or your data source.
        // For the sake of this example, we'll create dummy data.
        pendingOrdersList = getPendingOrders();

        // Set the pendingOrdersList to the RecyclerView Adapter
        OrderAdapter orderAdapter = new OrderAdapter(pendingOrdersList);
        recyclerView.setAdapter(orderAdapter);
    }

    private List<Order> getPendingOrders() {
        // Here you would fetch the list of pending orders from your data source.
        // Since you mentioned that someone else is dealing with the database, assume you have this method.
        // For now, let's return some dummy data for testing.

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1", "Cust001", "Product A", 5, "Pending"));
        orders.add(new Order("2", "Cust002", "Product B", 3, "Pending"));
        orders.add(new Order("3", "Cust001", "Product C", 2, "Pending"));
        // Add more orders as needed.
        return orders;
    }
}