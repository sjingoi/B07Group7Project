package com.example.b07group7project.ui.login;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter {
    LoginModel loginModel;
    LoginView loginView;

    public LoginPresenter(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }
    public void loginButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginView.setLoadingAnimation(true);
        loginModel.signIn(username, password, this::afterLoginAttempt);
    }

    public void registerButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginView.setLoadingAnimation(true);
        loginModel.register(username, password, this::afterRegistrationAttempt);
    }

    public boolean isValid(String user, String password) {
        if (user == null) {
            return false;
        }
        return password != null && password.trim().length() > 5;
    }

    public void forgotPasswordOnClick() {
        loginView.replaceFragment(new ResetPasswordFragment());
    }

    public void afterRegistrationAttempt(Task<AuthResult> task) {
        if (loginModel.getCurrentUser() != null) {
            loginView.replaceFragment(new RegisterFragment());
        } else {
            loginView.onRegistrationFailed();
        }
        loginView.setLoadingAnimation(false);
    }

    public void afterLoginAttempt(Task<AuthResult> task) {
        FirebaseUser user = loginModel.getCurrentUser();
        if (user != null) {
            loginModel.getUserType(user, type -> loginView.onLoginComplete(type));
        } else
            loginView.onLoginFailed();
        loginView.setLoadingAnimation(false);
    }
}
