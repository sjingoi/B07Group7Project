package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        binding.createShopperAccount.setOnClickListener(v-> registerPresenter.handleCreateShopperAccount());
        binding.createStoreOwnerAccount.setOnClickListener(v-> registerPresenter.handleCreateStoreOwnerAccount());

    }
}
