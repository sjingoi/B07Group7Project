package com.example.b07group7project.shopper_view_store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.Store;

import com.example.b07group7project.database.StoreDatabase;


import java.util.ArrayList;

public class ShopperViewStoreFragment extends Fragment implements StoreClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopper_view_store_fragment, container, false);

        GetStoreInterface storeInterface = new StoreDatabase();
        storeInterface.getStores(
                stores -> onReceivedStores(stores, view)
        );

        return view;
    }

    public void onReceivedStores(ArrayList<Store> stores, View view){
        RecyclerView recyclerView = view.findViewById(R.id.StoreListRecyclerView);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireContext(), stores, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

    }

    @Override
    public void onStoreClicked(Store store) {
        Toast.makeText(requireContext(), store.getStoreName(), Toast.LENGTH_SHORT).show();
    }

}
