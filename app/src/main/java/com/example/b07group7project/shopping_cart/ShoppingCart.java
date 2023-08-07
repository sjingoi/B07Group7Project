package com.example.b07group7project.shopping_cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b07group7project.R;

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

        (new GetCartEntriesImplementation()).getCartEntries(cartEntries -> onData(recyclerView, cartEntries));

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return shoppingCartLayout;
    }

    private void onData(RecyclerView recyclerView, List<CartEntry> cart) {
        recyclerView.setAdapter(new CartAdapter(requireContext().getApplicationContext(), cart));
    }
}