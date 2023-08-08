package com.example.b07group7project.store_owner_view_store;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.ExampleFragment;
import com.example.b07group7project.R;
import com.example.b07group7project.create_product.CreateProductFragment;
import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.view_products.GetProductsImplementation;
import com.example.b07group7project.view_products.GetProductsInterface;
import com.example.b07group7project.view_products.ViewProductFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreOwnerViewProducts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreOwnerViewProducts extends ViewProductFragment {
    public static StoreOwnerViewProducts newInstance() {
        StoreOwnerViewProducts fragment = new StoreOwnerViewProducts();
        Bundle args = new Bundle();
        //args.putString("storeID", storeID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_owner_view_products, container, false);

        FloatingActionButton addButton = view.findViewById(R.id.addItemButton);

        addButton.setOnClickListener(view1 -> {
            Activity activity = requireActivity();
            if (activity instanceof Navigation) {
                Navigation navigation = (Navigation) activity;
                navigation.replaceFragment(CreateProductFragment.newInstance(), true); //TODO: CHANGE THIS
            }
        });

        String storeUUID = "";

        // TODO: Replace GetProductImplementation with Database Stuff
        GetProductsInterface productInterface = new GetProductsImplementation();
        productInterface.getProducts(storeUUID, products -> onReceivedProcucts(products, view));

        return view;
    }

}