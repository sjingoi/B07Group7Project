package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07group7project.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    private LoginPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmailPasswordActivity activity = (EmailPasswordActivity)requireActivity();
        presenter = new LoginPresenter(
                activity.getLoginModel(),
                new LoginViewAdapter(this, activity));
        EditText usernameEditText = binding.username;
        EditText passwordEditText = binding.password;
        Button loginButton = binding.login;
        Button registerButton = binding.register;
        Button forgotPasswordButton = binding.forgotPassword;

        TextWatcher loginWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean areFieldsValid = presenter.isValid(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                loginButton.setEnabled(areFieldsValid);
                registerButton.setEnabled(areFieldsValid);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean areFieldsValid = presenter.isValid(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                loginButton.setEnabled(areFieldsValid);
                registerButton.setEnabled(areFieldsValid);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean areFieldsValid = presenter.isValid(usernameEditText.getText().toString(),
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
            presenter.loginButtonOnClick(usernameText, passwordText);
        });
        registerButton.setOnClickListener(v -> {
            String usernameText = usernameEditText.getText().toString();
            String passwordText = passwordEditText.getText().toString();
            presenter.registerButtonOnClick(usernameText, passwordText);
        });

        forgotPasswordButton.setOnClickListener(v -> presenter.forgotPasswordOnClick());

    }

    public void setLoadingAnimation(boolean shouldDisplay){
        int visibility = (shouldDisplay)? View.VISIBLE:View.INVISIBLE;
        binding.loading.setVisibility(visibility);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_LONG).show();
    }

    public void onRegistrationFailed() {
        Toast.makeText(getContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
        setLoadingAnimation(false);
    }
}