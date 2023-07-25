package com.example.b07group7project.ui.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.b07group7project.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    FirebaseAuth mAuth;
    LoginFragment loginFragment;

    public LoginPresenter(FirebaseAuth mAuth, LoginFragment loginFragment) {
        this.mAuth = mAuth;
        this.loginFragment = loginFragment;
        FragmentLoginBinding binding = loginFragment.binding;
        EditText usernameEditText = binding.username;
        EditText passwordEditText = binding.password;
        Button loginButton = binding.login;
        Button registerButton = binding.register;
        Button forgotPasswordButton = binding.forgotPassword;

        TextWatcher loginWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                boolean areFieldsValid = isValid(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                loginButton.setEnabled(areFieldsValid);
                registerButton.setEnabled(areFieldsValid);
            }
        };
        passwordEditText.addTextChangedListener(loginWatcher);
        usernameEditText.addTextChangedListener(loginWatcher);
        loginButton.setOnClickListener(v -> {
            String usernameText = usernameEditText.getText().toString();
            String passwordText = passwordEditText.getText().toString();
            loginButtonOnClick(usernameText, passwordText);
        });
        registerButton.setOnClickListener(v -> {
            String usernameText = usernameEditText.getText().toString();
            String passwordText = passwordEditText.getText().toString();
            registerButtonOnClick(usernameText, passwordText);
        });

        forgotPasswordButton.setOnClickListener(v -> {


        });
    }
    private void loginButtonOnClick(String username, String password){
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

    private void registerButtonOnClick(String username, String password){
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
    private boolean isValid(String user, String password) {
        if (user == null) {
            return false;
        }
        return password != null && password.trim().length() > 5;
    }
}
