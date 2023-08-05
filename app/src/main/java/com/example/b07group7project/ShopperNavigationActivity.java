package com.example.b07group7project;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.b07group7project.create_order.CheckoutFragment;
import com.example.b07group7project.databinding.ShopperNavigationActivityBinding;
import com.example.b07group7project.shopping_cart.ShoppingCart;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;

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

        homeFragment = ShopperViewStoreFragment.newInstance();
        cartFragment = ShoppingCart.newInstance();
        accountFragment = ExampleFragment.newInstance(); // CHANGE THIS

        replaceFragment(homeFragment, false);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeNav) {
                replaceFragment(homeFragment, false);
            } else if (itemId == R.id.cartNav) {
                replaceFragment(cartFragment, false);
            } else if (itemId == R.id.accountNav) {
                replaceFragment(accountFragment, false);
            }

            return true;
        });
    }

    @Override
    public int getFragmentContainer() {
        return R.id.frameLayout;
    }
}