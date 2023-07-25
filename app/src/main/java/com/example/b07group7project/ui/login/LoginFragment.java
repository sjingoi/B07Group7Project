package com.example.b07group7project.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.b07group7project.databinding.FragmentLoginBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private Button forgotPasswordButton;
    private ProgressBar loadingProgressBar;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
        registerButton = binding.register;
        loadingProgressBar = binding.loading;
        forgotPasswordButton = binding.forgotPassword;


        TextWatcher loginWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean areFieldsValid = isValid(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                loginButton.setEnabled(areFieldsValid);
                registerButton.setEnabled(areFieldsValid);
            }
        };
        passwordEditText.addTextChangedListener(loginWatcher);
        usernameEditText.addTextChangedListener(loginWatcher);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String usernameText = usernameEditText.getText().toString();
                String passwordText = passwordEditText.getText().toString();
                if(isValid(usernameText, passwordText))
                    mAuth.signInWithEmailAndPassword(usernameText, passwordText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    ((EmailPasswordActivity) getActivity()).updateUI(mAuth.getCurrentUser());
                                    loadingProgressBar.setVisibility(View.INVISIBLE);
                                }
                            });
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String usernameText = usernameEditText.getText().toString();
                String passwordText = passwordEditText.getText().toString();
                if(isValid(usernameText, passwordText))
                    mAuth.createUserWithEmailAndPassword(usernameText, passwordText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    ((EmailPasswordActivity) getActivity()).updateUI(mAuth.getCurrentUser());
                                    loadingProgressBar.setVisibility(View.INVISIBLE);
                                }
                            });
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    private boolean isValid(String user, String password) {
        if (user == null) {
            return false;
        }
        return password != null && password.trim().length() > 5;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}