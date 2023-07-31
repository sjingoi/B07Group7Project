package com.example.b07group7project.ui.login;

import android.widget.Toast;

import com.example.b07group7project.Navigation;
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
        loginModel.signIn(username, password, task -> {
                    if(loginModel.getCurrentUser() != null){
                        EmailPasswordActivity activity = (EmailPasswordActivity)loginFragment.requireActivity();
                        activity.onLoginCompete();
                    }

                    else
                        Toast.makeText(loginFragment.getContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    loginFragment.setLoadingAnimation(false);
                });
    }

    public void registerButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginFragment.setLoadingAnimation(true);
        loginModel.register(username, password, task -> {
                    FirebaseUser user = loginModel.getCurrentUser();
                    if(user == null){
                        onRegistrationFailed();
                        loginFragment.setLoadingAnimation(false);
                        return;
                    }

                    Navigation parent = (Navigation) loginFragment.requireActivity();
                    parent.replaceFragment(new RegisterFragment());
                    loginFragment.setLoadingAnimation(false);
                });
    }

    private void onRegistrationFailed() {
        Toast.makeText(loginFragment.getContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
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
}
