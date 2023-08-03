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

import java.util.ArrayList;

public class ShopperViewStoreFragment extends Fragment implements StoreClickListener {

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
        GetStoreInterface storeInterface = new GetStoreImplementation();
        //
        ArrayList<Store> StoreList = (ArrayList<Store>) storeInterface.getStores();
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireContext(), StoreList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return view;
    }

    @Override
    public void onStoreClicked(Store store) {
        Toast.makeText(requireContext(), store.getStoreName(), Toast.LENGTH_SHORT).show();
    }
}
