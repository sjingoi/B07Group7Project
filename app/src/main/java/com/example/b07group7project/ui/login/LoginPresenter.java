package com.example.b07group7project.ui.login;

import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    FirebaseAuth mAuth;
    LoginFragment loginFragment;

    public LoginPresenter(FirebaseAuth mAuth, LoginFragment loginFragment) {
        this.mAuth = mAuth;
        this.loginFragment = loginFragment;
    }
    public void loginButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginFragment.setLoadingAnimation(true);
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    ((EmailPasswordActivity) loginFragment.requireActivity())
                            .onLoginComplete(mAuth.getCurrentUser());
                    loginFragment.setLoadingAnimation(false);
                });
    }

    public void registerButtonOnClick(String username, String password){
        if(!isValid(username, password)){
            return;
        }
        loginFragment.setLoadingAnimation(true);
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    ((EmailPasswordActivity) loginFragment.requireActivity())
                            .onRegistrationComplete(mAuth.getCurrentUser());
                    loginFragment.setLoadingAnimation(false);
                });
    }
    public boolean isValid(String user, String password) {
        if (user == null) {
            return false;
        }
        return password != null && password.trim().length() > 5;
    }
}
