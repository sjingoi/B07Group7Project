package com.example.b07group7project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;

import java.util.ArrayList;
import java.util.List;

public class StoreOwnerOrdersFragment extends Fragment {
    private List<OrderedProduct> pendingOrdersList = new ArrayList<>();
    private OrderAdapter orderAdapter;

    public StoreOwnerOrdersFragment() {

    }

    public static StoreOwnerOrdersFragment newInstance() {
        StoreOwnerOrdersFragment fragment = new StoreOwnerOrdersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_owner_orders, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Initialize order adapter with  empty list initially
        orderAdapter = new OrderAdapter(pendingOrdersList);
        recyclerView.setAdapter(orderAdapter);

        // Fetch pending orders for  current store owner using  DummyOrder
        fetchPendingOrdersForStoreOwner();

        return rootView;
    }
    private void fetchPendingOrdersForStoreOwner() {
        DummyStoreData dummy = new DummyStoreData();

        dummy.getPendingOrdersForStoreOwner("Random Id Sowne", new OrderInterface.OrderCallback() {
            @Override
            public void onPendingOrdersLoaded(List<OrderedProduct> orders) {
                updateUIWithOrders(orders);
            }
        });
    }
    private void updateUIWithOrders(List<OrderedProduct> orders) {
        pendingOrdersList.clear();
        pendingOrdersList.addAll(orders);
        orderAdapter.notifyDataSetChanged();
    }
}
