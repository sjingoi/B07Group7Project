package com.example.b07group7project.ui.login;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.UserType;

public interface LoginView {
    void setLoadingAnimation(boolean isLoading);

    void replaceFragment(Fragment resetPasswordFragment);

    void onRegistrationFailed();

    void onLoginComplete(UserType userType);

    void onLoginFailed();
}
