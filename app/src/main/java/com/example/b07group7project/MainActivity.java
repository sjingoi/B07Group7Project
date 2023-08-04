package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.b07group7project.create_order.CheckoutFragment;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code For Running ShopperViewStoreFragment
//        if (savedInstanceState == null) {
//            // Create a new instance of the ShopperViewStoreFragment
//            ShopperViewStoreFragment shopperViewStoreFragment = new ShopperViewStoreFragment();
//
//            // Replace the existing content with the ShopperViewStoreFragment
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragment_container, shopperViewStoreFragment);
//            transaction.commit();
//      }

        // Code For Running CheckoutFragment
        if (savedInstanceState == null) {
            // Create a new instance of the ShopperViewStoreFragment
            CheckoutFragment checkoutFragment = new CheckoutFragment();

            // Replace the existing content with the ShopperViewStoreFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, checkoutFragment);
            transaction.commit();
      }
    }
}