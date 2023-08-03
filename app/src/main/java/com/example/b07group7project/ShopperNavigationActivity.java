package com.example.b07group7project;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.b07group7project.databinding.ShopperNavigationActivityBinding;

public class ShopperNavigationActivity extends Navigation {

    ShopperNavigationActivityBinding binding;

    Fragment homeFragment;
    Fragment cartFragment;
    Fragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ShopperNavigationActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment = new ShopperViewStoreFragment();    // CHANGE THIS
        cartFragment = new ExampleFragment();    // CHANGE THIS
        accountFragment = new ExampleFragment(); // CHANGE THIS

        replaceFragment(homeFragment);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeNav) {
                replaceFragment(homeFragment);
            } else if (itemId == R.id.cartNav) {
                replaceFragment(cartFragment);
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