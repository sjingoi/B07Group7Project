package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07group7project.databinding.FragmentRegisterAccountBinding;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    FragmentRegisterAccountBinding binding;
    FirebaseUser user;

    public RegisterFragment(FirebaseUser user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.createShopperAccount.setOnClickListener(v-> handleCreateShopperAccount());
        binding.createStoreOwnerAccount.setOnClickListener(v-> handleCreateStoreOwnerAccount());

    }

    private void handleCreateStoreOwnerAccount() {
        ((EmailPasswordActivity) requireActivity()).moveToStoreOwnerLandingPage(user);
    }

    private void handleCreateShopperAccount() {
        ((EmailPasswordActivity) requireActivity()).moveToShopperLandingPage(user);
    }
}
