package com.example.b07group7project.shopper_view_previous_orders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.Customer;

import java.util.ArrayList;

public class ShopperPreviousOrderActivity extends AppCompatActivity {

    ArrayList<PreviousOrder> previousOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_previousorders);
        RecyclerView previousorderview = findViewById(R.id.viewPreviousOrders);
        setUpPreviousOrders();
        PreviousOrderAdapter adapter = new PreviousOrderAdapter(this, previousOrders);
        previousorderview.setAdapter(adapter);
        previousorderview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpPreviousOrders() {
        previousOrders = getPreviousOrders(new Customer());
    }
}
