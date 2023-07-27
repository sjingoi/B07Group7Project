package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.b07group7project.databinding.ActivityFragmentNavigationBinding;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity implements FragmentSwitch {

    ActivityFragmentNavigationBinding binding;


    Fragment homeFragment;
    Fragment cartFragment;
    Fragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        homeFragment = intent.getStringExtra("home-fragment");
        cartFragment = intent.getStringExtra("cart-fragment");
        accountFragment = intent.getStringExtra("account-fragment");

        binding = ActivityFragmentNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        replaceFragment(new ExampleFragment());

//        homeFragment = new ExampleFragment();    // CHANGE THIS
//        cartFragment = new ExampleFragment();    // CHANGE THIS
//        accountFragment = new ExampleFragment(); // CHANGE THIS

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
    public void replaceFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, newFragment);
        fragmentTransaction.commit();
    }
}