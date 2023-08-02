package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserType;

public class RegisterPresenter {

    LoginModel loginModel;
    RegisterFragment registerFragment;

    public RegisterPresenter(RegisterFragment registerView, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.registerFragment = registerView;
    }

    public void handleCreateAccount(UserType userType) {
        if(loginModel.getCurrentUser() == null){
            registerFragment.showRegistrationFailedNotification();
            return;
        }

        loginModel.setUserType(userType, loginModel.getCurrentUser());
        ((EmailPasswordActivity) registerFragment.requireActivity()).onLoginCompete();
    }
}
