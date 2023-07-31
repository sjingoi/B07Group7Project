package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.widget.Toast;

import com.example.b07group7project.Navigation;
import com.example.b07group7project.R;
import com.example.b07group7project.UserType;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends Navigation {
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this.getApplicationContext());
        setContentView(R.layout.activity_shopper_email_login);
        loginModel = new LoginModelAdapter(FirebaseAuth.getInstance(), new TemporaryUserPermission());
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = loginModel.getCurrentUser();
        if(currentUser != null){
            loginModel.signOut();
//            onLoginComplete(currentUser);
        }
    }


    public void moveToShopperLandingPage(){
        //TODO: replace this with appropriate page
        Toast.makeText(this, "Login Succeeded - shopper", Toast.LENGTH_SHORT).show();
    }

    public void moveToStoreOwnerLandingPage(){
        //TODO: replace this with appropriate page
        Toast.makeText(this, "Login Succeeded - store owner", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getFragmentContainer() {
        return R.id.fragmentContainerView;
    }

    public LoginModel getLoginModel(){
        return loginModel;
    }

    public void onLoginCompete() {
        UserType type = loginModel.getUserType(loginModel.getCurrentUser());
        if(type == UserType.SHOPPER)
            moveToShopperLandingPage();
        else
            moveToStoreOwnerLandingPage();
    }
}