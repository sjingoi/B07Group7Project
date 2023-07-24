package com.example.b07group7project.shopping_cart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b07group7project.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Fragment {



    public ShoppingCart() {
        // Required empty public constructor
    }

//    public static ShoppingCart newInstance() {
//        ShoppingCart fragment = new ShoppingCart();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

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

        List<CartItem> cart = new ArrayList<>();

        cart.add(new CartItem("Test Product", 2, "https://fastly.picsum.photos/id/554/200/300.jpg?hmac=fYkNLoTqHRKUkIc3bZt_xMEb17s_BIRuuKTz8jb9ijs")); // PLACEHOLDER DATA

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(new CartAdapter(requireContext().getApplicationContext(), cart));

        return shoppingCartLayout;
    }
}