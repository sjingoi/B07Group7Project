package com.example.b07group7project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Create a new instance of the ShopperViewStoreFragment
            ShopperViewStoreFragment shopperViewStoreFragment = new ShopperViewStoreFragment();

            // Replace the existing content with the ShopperViewStoreFragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, shopperViewStoreFragment);
            transaction.commit();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
//        Intent myIntent = new Intent(this, EmailPasswordActivity.class);
//        startActivity(myIntent);
    }
}