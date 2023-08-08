package com.example.b07group7project.nav;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.b07group7project.ExampleFragment;
import com.example.b07group7project.R;
import com.example.b07group7project.StoreOwnerOrdersFragment;
import com.example.b07group7project.databinding.ActivityStoreOwnerNavigationBinding;

public class StoreOwnerNavigationActivity extends Navigation {

    ActivityStoreOwnerNavigationBinding binding;

    Fragment homeFragment;
    Fragment cartFragment;
    Fragment accountFragment;
    Fragment storeOwnerOrdersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStoreOwnerNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment = ExampleFragment.newInstance();    // CHANGE THIS
        cartFragment = ExampleFragment.newInstance();    // CHANGE THIS
        accountFragment = ExampleFragment.newInstance(); // CHANGE THIS
        storeOwnerOrdersFragment = StoreOwnerOrdersFragment.newInstance(); // Instantiate your StoreOwnerOrdersFragment


        replaceFragment(homeFragment);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeNav) {
                replaceFragment(homeFragment);
            } else if (itemId == R.id.cartNav) {
                replaceFragment(storeOwnerOrdersFragment); // Replace with StoreOwnerOrdersFragment
            } else if (itemId == R.id.accountNav) {
                replaceFragment(accountFragment);
            }

            return true;
        });


    }

    @Override
    public int getFragmentContainer() {
        return R.id.frameLayout;
    }
}