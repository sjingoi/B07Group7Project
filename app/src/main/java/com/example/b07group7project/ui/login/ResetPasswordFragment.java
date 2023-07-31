package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07group7project.Navigation;
import com.example.b07group7project.databinding.FragmentResetPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordFragment extends Fragment {

    FragmentResetPasswordBinding binding;
    FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentResetPasswordBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sendResetLinkButton.setOnClickListener(
                v->{
                    mAuth.sendPasswordResetEmail(binding.editTextTextEmailAddress.getText().toString());
                    Toast.makeText(getContext(), "Email Sent", Toast.LENGTH_LONG).show();
                });

        binding.button.setOnClickListener(
                v-> ((Navigation) requireActivity()).replaceFragment(new LoginFragment())
        );

    }


}
