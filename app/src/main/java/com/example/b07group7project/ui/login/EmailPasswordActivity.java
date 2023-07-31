package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.widget.Toast;

import com.example.b07group7project.Navigation;
import com.example.b07group7project.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends Navigation {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this.getApplicationContext());
        setContentView(R.layout.activity_shopper_email_login);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mAuth.signOut();
//            onLoginComplete(currentUser);
        }
    }


    public void onLoginComplete(FirebaseUser user) {
        if(isShopper(user)){
            moveToShopperLandingPage(user);
        }
        else{
            moveToStoreOwnerLandingPage(user);
        }
    }



    public void moveToShopperLandingPage(FirebaseUser user){
        //TODO: replace this with appropriate page
        Toast.makeText(this, "Login Succeeded - shopper", Toast.LENGTH_SHORT).show();
    }

    public void moveToStoreOwnerLandingPage(FirebaseUser user){
        //TODO: replace this with appropriate page
        Toast.makeText(this, "Login Succeeded - store owner", Toast.LENGTH_SHORT).show();
    }

    public boolean isShopper(FirebaseUser user){
        //TODO
        return true;
    }

    @Override
    public int getFragmentContainer() {
        return R.id.fragmentContainerView;
    }
}