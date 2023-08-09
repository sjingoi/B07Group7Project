package com.example.b07group7project.nav;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.example.b07group7project.R;
import com.example.b07group7project.account.StoreOwnerAccountFragment;
import com.example.b07group7project.create_product.CreateProductFragment;
import com.example.b07group7project.databinding.ActivityStoreOwnerNavigationBinding;
import com.example.b07group7project.store_owner_view_store.StoreOwnerViewProducts;

public class StoreOwnerNavigationActivity extends Navigation {

    ActivityStoreOwnerNavigationBinding binding;

    Fragment homeFragment;
    Fragment cartFragment;
    Fragment accountFragment;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStoreOwnerNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSystemBarColors();

        toolbar = findViewById(R.id.toolbar);

        setUpToolbar();


        homeFragment = StoreOwnerViewProducts.newInstance();    // CHANGE THIS
        cartFragment = CreateProductFragment.newInstance();    // CHANGE THIS
        accountFragment = StoreOwnerAccountFragment.newInstance(); // CHANGE THIS


        replaceFragment(homeFragment, false, "Products");

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeNav) {
                replaceFragment(homeFragment, false, "Products");
            } else if (itemId == R.id.cartNav) {
                replaceFragment(cartFragment, false, "Orders");
            } else if (itemId == R.id.accountNav) {
                replaceFragment(accountFragment, false, "Store Account");
            }

            return true;
        });
    }

    @Override
    public int getFragmentContainer() {
        return R.id.frameLayout;
    }


    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}