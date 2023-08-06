package com.example.b07group7project.ui.login;

import androidx.fragment.app.Fragment;

public interface LoginView {
    void setLoadingAnimation(boolean isLoading);

    void replaceFragment(Fragment resetPasswordFragment);

    void onRegistrationFailed();

    void onLoginComplete();

    void onLoginFailed();
}
