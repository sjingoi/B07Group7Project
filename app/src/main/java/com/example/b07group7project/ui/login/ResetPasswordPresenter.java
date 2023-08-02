package com.example.b07group7project.ui.login;

public class ResetPasswordPresenter {
    ResetPasswordFragment resetPasswordFragment;
    LoginModel loginModel;
    public ResetPasswordPresenter(ResetPasswordFragment resetPasswordFragment,
                                  LoginModel loginModel) {
        this.resetPasswordFragment = resetPasswordFragment;
        this.loginModel = loginModel;
    }

    public void onGoBack() {
        ((EmailPasswordActivity) resetPasswordFragment.requireActivity())
                .replaceFragment(new LoginFragment());
    }

    public void onSendResetLink() {
        loginModel.sendResetLink(resetPasswordFragment.getEmail());
        resetPasswordFragment.showEmailSentNotification();
    }
}
