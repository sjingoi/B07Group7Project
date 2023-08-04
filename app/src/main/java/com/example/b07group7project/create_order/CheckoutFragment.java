package com.example.b07group7project.create_order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;


import java.util.ArrayList;

public class CheckoutFragment extends Fragment {
    public CheckoutFragment() {
        // Empty Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.Checkout_RecyclerView);
////        // Lines where getCart is used ----------------
        GetCartInterface cartInterface = new GetCartImplementation();
        ArrayList<Product> cart = (ArrayList<Product>) cartInterface.getCart("ExampleUser");
////        // --------------------------------------------
//
        CheckoutRecyclerAdapter adapter = new CheckoutRecyclerAdapter(requireContext(), cart);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

}
