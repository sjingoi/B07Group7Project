package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import com.example.b07group7project.databinding.ActivityFragmentNavigationBinding;

public class NavigationActivity extends AppCompatActivity implements FragmentSwitch {

    ActivityFragmentNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ExampleFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.homeNav) {
                replaceFragment(new ExampleFragment()); // CHANGE THIS LATER
            } else if (itemId == R.id.cartNav) {
                replaceFragment(new ExampleFragment()); // CHANGE THIS LATER
            } else if (itemId == R.id.accountNav) {
                replaceFragment(new ExampleFragment()); // CHANGE THIS LATER
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