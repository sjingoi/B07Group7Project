package com.example.b07group7project.store_owner_view_store;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.R;
import com.example.b07group7project.create_product.CreateProductFragment;
import com.example.b07group7project.database.StoreProductDatabase;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.itempreview.ItemPreviewFragment;
import com.example.b07group7project.itempreview.OwnerItemPreviewFragment;
import com.example.b07group7project.nav.Navigation;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the store ID from arguments
        Bundle arguments = getArguments();
        if (arguments != null) {
            storeID = arguments.getString("storeID");
            // Now you can use the storeID variable as needed
        }
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

        GetProductsInterface productInterface = new StoreProductDatabase();
        productInterface.getProducts(storeID, products -> onReceivedProducts(products, view));

        return view;
    }


    @Override
    public void onProductClicked(StoreProduct product) {
        if (requireActivity() instanceof Navigation) {
            Navigation nav = (Navigation) requireActivity();
            Bundle bundle = new Bundle();
            bundle.putString("itemID", product.getProductID());
            bundle.putString("storeID", product.getStoreID());
            nav.replaceFragment(OwnerItemPreviewFragment.newInstance(), true, bundle);
        }
        //Toast.makeText(requireContext(), product.getItemName(), Toast.LENGTH_SHORT).show();
    }

}