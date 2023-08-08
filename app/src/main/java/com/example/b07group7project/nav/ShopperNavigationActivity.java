package com.example.b07group7project.nav;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import com.example.b07group7project.ExampleFragment;
import com.example.b07group7project.R;
import com.example.b07group7project.databinding.ShopperNavigationActivityBinding;
import com.example.b07group7project.shopping_cart.ShoppingCartFragment;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;

public class ShopperNavigationActivity extends Navigation {

    ShopperNavigationActivityBinding binding;

    Fragment homeFragment;
    Fragment cartFragment;
    Fragment accountFragment;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.nav_background_color));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.nav_background_color));

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