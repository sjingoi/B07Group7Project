package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserType;

public class RegisterPresenter {

    LoginModel loginModel;
    EmailPasswordActivity emailPasswordActivity;

    public RegisterPresenter(EmailPasswordActivity emailPasswordActivity, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.emailPasswordActivity = emailPasswordActivity;
    }

    public void handleCreateAccount(UserType userType) {
        loginModel.setUserType(userType, loginModel.getCurrentUser());
        emailPasswordActivity.onLoginCompete();
    }
}
