package com.example.b07group7project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopperViewStoreFragment extends Fragment {

    public ShopperViewStoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopper_view_store_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.StoreListRecyclerView);
        // Line where getStores is used
        ArrayList<Store> StoreList = GetStoreImplementation.getStores();
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireContext(), StoreList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return view;
    }
}
