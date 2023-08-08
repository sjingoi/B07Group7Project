package com.example.b07group7project.nav;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.example.b07group7project.ExampleFragment;
import com.example.b07group7project.R;
import com.example.b07group7project.databinding.ShopperNavigationActivityBinding;
import com.example.b07group7project.shopping_cart.ShoppingCartFragment;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;

public class ShopperNavigationActivity extends Navigation {

    ShopperNavigationActivityBinding binding;

    NavigationFragment homeFragment;
    NavigationFragment cartFragment;
    NavigationFragment accountFragment;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ShopperNavigationActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);

        homeFragment = ShopperViewStoreFragment.newInstance();
        cartFragment = ShoppingCartFragment.newInstance();
        accountFragment = ExampleFragment.newInstance(); // CHANGE THIS

        replaceFragment(homeFragment, false, "Home");

        setUpToolbar();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeNav) {
                replaceFragment(homeFragment, false, "Home");
            } else if (itemId == R.id.cartNav) {
                replaceFragment(cartFragment, false, "Shopping Cart");
            } else if (itemId == R.id.accountNav) {
                replaceFragment(accountFragment, false, "Account");
            }

            return true;
        });
    }



    public int getFragmentContainer() {
        return R.id.frameLayout;
    }



    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

}