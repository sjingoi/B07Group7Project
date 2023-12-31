package com.example.b07group7project.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.b07group7project.R;
import com.example.b07group7project.UserType;
import com.example.b07group7project.database.AccountDatabase;
import com.example.b07group7project.database.User;
import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.nav.ShopperNavigationActivity;
import com.example.b07group7project.nav.StoreOwnerNavigationActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class EmailPasswordActivity extends AppCompatActivity {
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this.getApplicationContext());
        setContentView(R.layout.activity_shopper_email_login);
        loginModel = new LoginModelAdapter(FirebaseAuth.getInstance(), new AccountDatabase());
    }

    @Override
    public void onStart(){
        super.onStart();
        User currentUser = loginModel.getCurrentUser();
        if(currentUser != null){
            loginModel.signOut();
//            onLoginComplete(currentUser);
        }
    }


    public void moveToShopperLandingPage(){
        Intent intent = new Intent(this, ShopperNavigationActivity.class);
        startActivity(intent);
        finish();
    }

    public void moveToStoreOwnerLandingPage(){
        Intent intent = new Intent(this, StoreOwnerNavigationActivity.class);
        startActivity(intent);
        finish();
    }

    public void replaceFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(getFragmentContainer(), newFragment);
        fragmentTransaction.commit();
    }

    public int getFragmentContainer() {
        return R.id.fragmentContainerView;
    }

    public LoginModel getLoginModel(){
        return loginModel;
    }

    public void onLoginCompete(UserType type) {
        if(type == UserType.SHOPPER)
            moveToShopperLandingPage();
        else
            moveToStoreOwnerLandingPage();
    }
}