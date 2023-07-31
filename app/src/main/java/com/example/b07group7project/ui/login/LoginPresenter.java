package com.example.b07group7project.ui.login;

import android.widget.Toast;

import com.example.b07group7project.Navigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                    FirebaseUser user = mAuth.getCurrentUser();
                    if(user == null){
                        onRegistrationFailed();
                        return;
                    }

                    Navigation parent = (Navigation) loginFragment.requireActivity();
                    parent.replaceFragment(new RegisterFragment(user));
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
        Navigation parent = (Navigation) loginFragment.requireActivity();
        parent.replaceFragment(new ResetPasswordFragment());
    }
}
