package com.example.b07group7project.ui.login;

import android.view.View;

public class ResetPasswordPresenter {
    ResetPasswordFragment resetPasswordFragment;
    LoginModel loginModel;
    public ResetPasswordPresenter(ResetPasswordFragment resetPasswordFragment,
                                  LoginModel loginModel) {
        this.resetPasswordFragment = resetPasswordFragment;
        this.loginModel = loginModel;
    }

    public void onGoBack(View ignoredV) {
        ((EmailPasswordActivity) resetPasswordFragment.requireActivity())
                .replaceFragment(new LoginFragment());
    }

    public void onSendResetLink(View ignoredV) {
        loginModel.sendResetLink(resetPasswordFragment.getEmail());
        resetPasswordFragment.showEmailSentNotification();
    }
}
