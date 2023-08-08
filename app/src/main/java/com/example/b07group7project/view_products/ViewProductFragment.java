package com.example.b07group7project.view_products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.itempreview.ItemPreviewFragment;
import com.example.b07group7project.nav.Navigation;


import java.util.ArrayList;

public class ViewProductFragment extends Fragment implements ProductClickListener{

    public ViewProductFragment() {
        // Required Empty Constructor
    }

    public static ViewProductFragment newInstance() {
        ViewProductFragment fragment = new ViewProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_product_fragment, container, false);

        // TODO: Replace GetProductImplementation with Database Stuff
        GetProductsInterface productInterface = new GetProductsImplementation();
        productInterface.getProducts(
                products -> onReceivedStores(products, view)
        );

        return view;
    }

    public void onReceivedStores(ArrayList<StoreProduct> products, View view){
        RecyclerView recyclerView = view.findViewById(R.id.ProductListRecyclerView);
        ViewProductAdapter adapter = new ViewProductAdapter(requireContext(), products, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

    }

    /*This is where button is clicked, when implementing navigation between fragments
    then update code in here */
    @Override
    public void onProductClicked(StoreProduct product) {
        if (requireActivity() instanceof Navigation) {
            Navigation nav = (Navigation) requireActivity();
            nav.replaceFragment(ItemPreviewFragment.newInstance(product), true);
        }
        Toast.makeText(requireContext(), product.getItemName(), Toast.LENGTH_SHORT).show();
    }

}
