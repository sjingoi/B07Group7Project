package com.example.b07group7project.ui.login;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.UserType;

public class LoginViewAdapter implements LoginView {
    LoginFragment loginFragment;
    EmailPasswordActivity activity;

    public LoginViewAdapter(LoginFragment fragment, EmailPasswordActivity activity){
        this.loginFragment = fragment;
        this.activity = activity;
    }

    @Override
    public void setLoadingAnimation(boolean isLoading) {
        loginFragment.setLoadingAnimation(isLoading);
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        activity.replaceFragment(fragment);
    }

    @Override
    public void onRegistrationFailed() {
        loginFragment.onRegistrationFailed();
    }

    @Override
    public void onLoginComplete(UserType type) {
        activity.onLoginCompete(type);
    }

    @Override
    public void onLoginFailed() {
        loginFragment.onLoginFailed();
    }
}
