package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07group7project.UserType;
import com.example.b07group7project.databinding.FragmentRegisterAccountBinding;

public class RegisterFragment extends Fragment {

    FragmentRegisterAccountBinding binding;
    RegisterPresenter registerPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterAccountBinding.inflate(getLayoutInflater());
        registerPresenter = new RegisterPresenter(this,
                ((EmailPasswordActivity)requireActivity()).getLoginModel());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.createShopperAccount.setOnClickListener(v-> registerPresenter.handleCreateAccount(UserType.SHOPPER));
        binding.createStoreOwnerAccount.setOnClickListener(v-> registerPresenter.handleCreateAccount(UserType.STORE_OWNER));

    }

    public void showRegistrationFailedNotification() {
        Toast.makeText(requireContext(), "Account creation failed", Toast.LENGTH_LONG).show();
    }
}
