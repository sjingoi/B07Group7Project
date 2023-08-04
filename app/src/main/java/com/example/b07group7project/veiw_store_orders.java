package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.List;

public class veiw_store_orders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veiw_store_orders);
    }

    private void display_order(List <order> orders) {
        OrderViewAdapter adapter = new OrderViewAdapter(this,orders);
        recycler_order.setAdapter(adapter);
    }
}