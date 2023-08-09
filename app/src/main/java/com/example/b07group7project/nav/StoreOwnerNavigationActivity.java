package com.example.b07group7project.nav;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.b07group7project.R;
import com.example.b07group7project.account.StoreOwnerAccountFragment;
import com.example.b07group7project.create_order.GetCartImplementation;
import com.example.b07group7project.create_order.GetCartInterface;
import com.example.b07group7project.create_product.CreateProductFragment;
import com.example.b07group7project.database.AccountDatabase;
import com.example.b07group7project.database.User;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.databinding.ActivityStoreOwnerNavigationBinding;
import com.example.b07group7project.store_owner_view_store.StoreOwnerViewProducts;
import com.example.b07group7project.view_products.ViewProductFragment;

public class StoreOwnerNavigationActivity extends Navigation {

    ActivityStoreOwnerNavigationBinding binding;

    // IK this kinda scuffed but we gotta pass the store
    String storeUUID;

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


        homeFragment = StoreOwnerViewProducts.newInstance();    // CHANGE THIS
        cartFragment = CreateProductFragment.newInstance();    // CHANGE THIS
        accountFragment = StoreOwnerAccountFragment.newInstance(); // CHANGE THIS


        replaceFragment(homeFragment);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();

            if (itemId == R.id.homeNav) {
                getstore();

                Bundle bundle = new Bundle();
                bundle.putString("storeID", storeUUID);

                replaceFragment(homeFragment, false, bundle);
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

    public void getstore(){
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.getStoreUUID(User.getCurrentUser(),
                this::onReceivedStore
        );
    }

    public void onReceivedStore(String storeUUID){
        this.storeUUID = storeUUID;
    }
}