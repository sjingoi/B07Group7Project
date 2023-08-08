package com.example.b07group7project.shopper_view_store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.R;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database.StoreDatabase;
import com.example.b07group7project.database_abstractions.StoreHeader;
import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.view_products.ViewProductFragment;

import java.util.ArrayList;

public class ShopperViewStoreFragment extends Fragment implements StoreClickListener {


    public ShopperViewStoreFragment() {
        // Required Empty Constructor
    }

    public static ShopperViewStoreFragment newInstance() {
        ShopperViewStoreFragment fragment = new ShopperViewStoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopper_view_store_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GetStoreInterface storeInterface = new StoreDatabase();
        storeInterface.getStores(stores -> onReceivedStores(stores, view));
    }

    public void onReceivedStores(ArrayList<StoreHeader> stores, View view){
        RecyclerView recyclerView = view.findViewById(R.id.StoreListRecyclerView);
        StoreRecyclerViewAdapter adapter = new StoreRecyclerViewAdapter(requireContext(), stores, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

    }

    @Override
    public void onStoreClicked(StoreHeader store) {
        if (requireActivity() instanceof Navigation) {
            Navigation nav = (Navigation) requireActivity();
            Bundle bundle = new Bundle();
            bundle.putString("storeID", store.getUuid());
            nav.replaceFragment(ViewProductFragment.newInstance(), true, bundle); // TODO: Pass store data to the next fragment so it knows what to load (Seb will probably do this unless you want to then go ahead :) )
        }
//        Toast.makeText(requireContext(), store.getStoreName(), Toast.LENGTH_SHORT).show();
    }

}
