package com.example.b07group7project.ui.login;

public class ResetPasswordPresenter {
    ResetPasswordView resetPasswordView;
    LoginModel loginModel;
    public ResetPasswordPresenter(ResetPasswordView resetPasswordFragment,
                                  LoginModel loginModel) {
        this.resetPasswordView = resetPasswordFragment;
        this.loginModel = loginModel;
    }

    public void onGoBack() {
        resetPasswordView.replaceFragment(new LoginFragment());
    }

    public void onSendResetLink() {
        loginModel.sendResetLink(resetPasswordView.getEmail());
        resetPasswordView.showEmailSentNotification();
    }
}
