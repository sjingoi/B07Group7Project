package com.example.b07group7project.ui.login;

import androidx.fragment.app.Fragment;

public interface ResetPasswordView {
    void replaceFragment(Fragment fragment);
    String getEmail();
    void showEmailSentNotification();
}
