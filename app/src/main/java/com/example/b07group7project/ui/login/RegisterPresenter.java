package com.example.b07group7project.ui.login;

import android.widget.Toast;

import com.example.b07group7project.UserType;

public class RegisterPresenter {

    LoginModel loginModel;
    RegisterFragment registerFragment;

    public RegisterPresenter(RegisterFragment registerView, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.registerFragment = registerView;
    }

    public void handleCreateStoreOwnerAccount() {
        if(loginModel.getCurrentUser() == null){
            Toast.makeText(registerFragment.requireContext(), "Account creation failed", Toast.LENGTH_LONG).show();
            return;
        }

        loginModel.setUserType(UserType.STORE_OWNER, loginModel.getCurrentUser());
        ((EmailPasswordActivity) registerFragment.requireActivity()).moveToStoreOwnerLandingPage();
    }

    public void handleCreateShopperAccount() {
        if(loginModel.getCurrentUser() == null){
            Toast.makeText(registerFragment.requireContext(), "Account creation failed", Toast.LENGTH_LONG).show();
            return;
        }
        loginModel.setUserType(UserType.SHOPPER, loginModel.getCurrentUser());
        ((EmailPasswordActivity) registerFragment.requireActivity()).moveToShopperLandingPage();
    }
}
