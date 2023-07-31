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
    LoginModel loginModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterAccountBinding.inflate(getLayoutInflater());
        loginModel = ((EmailPasswordActivity)requireActivity()).getLoginModel();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.createShopperAccount.setOnClickListener(v-> handleCreateShopperAccount());
        binding.createStoreOwnerAccount.setOnClickListener(v-> handleCreateStoreOwnerAccount());

    }

    private void handleCreateStoreOwnerAccount() {
        if(loginModel.getCurrentUser() == null){
            Toast.makeText(requireContext(), "Account creation failed", Toast.LENGTH_LONG).show();
            return;
        }

        loginModel.setUserType(UserType.STORE_OWNER, loginModel.getCurrentUser());
        ((EmailPasswordActivity) requireActivity()).moveToStoreOwnerLandingPage();
    }

    private void handleCreateShopperAccount() {
        if(loginModel.getCurrentUser() == null){
            Toast.makeText(requireContext(), "Account creation failed", Toast.LENGTH_LONG).show();
            return;
        }
        loginModel.setUserType(UserType.SHOPPER, loginModel.getCurrentUser());
        ((EmailPasswordActivity) requireActivity()).moveToShopperLandingPage();
    }
}
