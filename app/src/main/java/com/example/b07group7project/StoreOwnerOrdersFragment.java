package com.example.b07group7project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreOwnerOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class StoreOwnerOrdersFragment extends Fragment {
    private List<Order> pendingOrdersList = new ArrayList<>();
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
        // Use  DummyOrder to pending orders for the current store owner
        DummyOrder dummyOrderRepository = new DummyOrder();

        // assume the store owner has the ID "store_owner_id" (can replace this with the actual store owner ID)
        dummyOrderRepository.getPendingOrdersForStoreOwner("store_owner_id", new OrderInterface.OrderCallback() {
            @Override
            public void onPendingOrdersLoaded(List<Order> orders) {
                // Orders loaded successfully, update the UI with the orders
                updateUIWithOrders(orders);
            }

            /*
            @Override
            public void onOrderConfirmationSuccess() {
                // Order confirmation successful, handle the success case if needed doing later.
            }
            */


            /*@Override
            public void onOrderConfirmationFailure(String errorMessage) {
                // Handle the error, e.g., display a message to the user. doing alter
            }
             */
        });
    }

    private void updateUIWithOrders(List<Order> orders) {
        pendingOrdersList.clear();
        pendingOrdersList.addAll(orders);
        orderAdapter.notifyDataSetChanged();
    }
}
