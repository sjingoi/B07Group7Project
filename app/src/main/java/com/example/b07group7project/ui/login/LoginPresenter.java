package com.example.b07group7project.ui.login;

import com.example.b07group7project.Navigation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    LoginModel loginModel;
    LoginFragment loginFragment;

    public LoginPresenter(LoginModel loginModel, LoginFragment loginFragment) {
        this.loginModel = loginModel;
        this.loginFragment = loginFragment;
    }
    public void loginButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginFragment.setLoadingAnimation(true);
        loginModel.signIn(username, password, this::onLoginComplete);
    }

    public void registerButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginFragment.setLoadingAnimation(true);
        loginModel.register(username, password, this::onRegistrationComplete);
    }

    public boolean isValid(String user, String password) {
        if (user == null) {
            return false;
        }
        return password != null && password.trim().length() > 5;
    }

    public void forgotPasswordOnClick() {
        EmailPasswordActivity parent = (EmailPasswordActivity) loginFragment.requireActivity();
        parent.replaceFragment(new ResetPasswordFragment());
    }

    private void onRegistrationComplete(Task<AuthResult> task) {
        FirebaseUser user = loginModel.getCurrentUser();
        if (user == null) {
            loginFragment.onRegistrationFailed();
            return;
        }

        Navigation parent = (Navigation) loginFragment.requireActivity();
        parent.replaceFragment(new RegisterFragment());
        loginFragment.setLoadingAnimation(false);
    }

    private void onLoginComplete(Task<AuthResult> task) {
        if (loginModel.getCurrentUser() != null) {
            EmailPasswordActivity activity = (EmailPasswordActivity) loginFragment.requireActivity();
            activity.onLoginCompete();
        } else
            loginFragment.showLoginFailedNotification();
        loginFragment.setLoadingAnimation(false);
    }
}
