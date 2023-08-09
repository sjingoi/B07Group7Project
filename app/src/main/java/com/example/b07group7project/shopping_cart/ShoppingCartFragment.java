package com.example.b07group7project.shopping_cart;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.b07group7project.R;
import com.example.b07group7project.itempreview.ItemPreviewFragment;
import com.example.b07group7project.nav.Navigation;

import java.util.List;
public class ShoppingCartFragment extends Fragment implements EntryClickListener {

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
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
        // Inflate the layout for this fragment
        View shoppingCartLayout = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        RecyclerView recyclerView = shoppingCartLayout.findViewById(R.id.cartItemList);

        (new GetCartEntriesImplementation()).getCartEntries(cartEntries -> onData(recyclerView, cartEntries));

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return shoppingCartLayout;
    }

    private void onData(RecyclerView recyclerView, List<CartEntry> cart) {
        recyclerView.setAdapter(new CartAdapter(requireContext().getApplicationContext(), cart, this));
    }

    @Override
    public void onEntryClick(CartEntry entry) {
        Activity activity = requireActivity();
        if (activity instanceof Navigation) {
            Navigation nav = (Navigation) activity;
            Bundle bundle = new Bundle();
            bundle.putString("itemID", entry.getProduct().getProductID());
            bundle.putString("storeID", entry.getStore());
            nav.replaceFragment(ItemPreviewFragment.newInstance(), true, bundle);
        }
    }

    @Override
    public void onRemoveClick(CartEntry entry) {
        //Delete Entry
        Toast.makeText(requireContext(), "DELETE", Toast.LENGTH_SHORT).show();
    }
}