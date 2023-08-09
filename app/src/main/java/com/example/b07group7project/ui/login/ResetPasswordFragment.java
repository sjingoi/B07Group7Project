package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07group7project.databinding.FragmentResetPasswordBinding;

public class ResetPasswordFragment extends Fragment implements ResetPasswordView {

    FragmentResetPasswordBinding binding;
    ResetPasswordPresenter resetPasswordPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentResetPasswordBinding.inflate(getLayoutInflater());
        resetPasswordPresenter = new ResetPasswordPresenter(this,
                ((EmailPasswordActivity)requireActivity()).getLoginModel());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.sendResetLinkButton.setOnClickListener(v -> resetPasswordPresenter.onSendResetLink());
        binding.button.setOnClickListener(v -> resetPasswordPresenter.onGoBack());
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        ((EmailPasswordActivity) requireActivity()).replaceFragment(fragment);
    }

    public String getEmail() {
        return binding.editTextTextEmailAddress.getText().toString();
    }

    public void showEmailSentNotification() {
        Toast.makeText(getContext(), "Email Sent", Toast.LENGTH_LONG).show();
    }

}
