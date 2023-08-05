package com.example.b07group7project.shopping_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.b07group7project.ExampleFragment;
import com.example.b07group7project.FragmentSwitch;
import com.example.b07group7project.R;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;
import java.util.List;
public class ShoppingCart extends Fragment {

    public ShoppingCart() {
        // Required empty public constructor
    }

    public static ShoppingCart newInstance() {
        ShoppingCart fragment = new ShoppingCart();
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

        List<CartEntry> cart = new ArrayList<>();

        View.OnClickListener cartOnClick = view -> {

            if (requireContext() instanceof FragmentSwitch) {
                FragmentSwitch fragmentSwitch = (FragmentSwitch) requireContext();
                fragmentSwitch.replaceFragment(ExampleFragment.newInstance(), true);
            }


        };
        StoreProduct sp = new StoreProduct("Test Product", "hi", "https://fastly.picsum.photos/id/554/200/300.jpg?hmac=fYkNLoTqHRKUkIc3bZt_xMEb17s_BIRuuKTz8jb9ijs", 69.99);
        Store store = null;
        cart.add(new CartEntry(sp, store, 2, cartOnClick)); // PLACEHOLDER DATA

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new CartAdapter(requireContext().getApplicationContext(), cart));

        return shoppingCartLayout;
    }
}