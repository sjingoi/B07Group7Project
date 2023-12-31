package com.example.b07group7project.nav;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.b07group7project.R;
import com.example.b07group7project.StoreOwnerOrdersFragment;
import com.example.b07group7project.account.StoreOwnerAccountFragment;
import com.example.b07group7project.create_product.CreateProductFragment;
import com.example.b07group7project.database.AccountDatabase;
import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database.User;
import com.example.b07group7project.databinding.ActivityStoreOwnerNavigationBinding;
import com.example.b07group7project.store_owner_view_store.StoreOwnerViewProducts;

public class StoreOwnerNavigationActivity extends Navigation {

    String storeUUID;
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

        homeFragment = StoreOwnerViewProducts.newInstance();    // CHANGE THIS
        cartFragment = StoreOwnerOrdersFragment.newInstance();    // CHANGE THIS
        accountFragment = StoreOwnerAccountFragment.newInstance(); // CHANGE THIS

        setUpToolbar();


        replaceFragment(accountFragment, false, "Account");

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            Bundle bundle = new Bundle();
            if (itemId == R.id.homeNav) {
                if(storeUUID != null){
                    bundle.putString("storeID", storeUUID);
                    replaceFragment(homeFragment, false, bundle, "Your Products");
                }
                else{
                    getStoreUUID(
                            uuid -> {
                                bundle.putString("storeID", uuid);
                                storeUUID = uuid;
                                replaceFragment(homeFragment, false, bundle, "Your Products");
                            });
                }

            } else if (itemId == R.id.cartNav) {
                if(storeUUID != null){
                    bundle.putString("storeID", storeUUID);
                    replaceFragment(cartFragment, false, "Orders");
                }
                else{
                    getStoreUUID(
                            uuid -> {
                                bundle.putString("storeID", uuid);
                                storeUUID = uuid;
                                replaceFragment(cartFragment, false, "Orders");
                            });
                }
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

    public void getStoreUUID(OnComplete<String> withUUID){
        AccountDatabase accountDatabase = new AccountDatabase();
        accountDatabase.getStoreUUID(User.getCurrentUser(),
                withUUID
        );
    }

}