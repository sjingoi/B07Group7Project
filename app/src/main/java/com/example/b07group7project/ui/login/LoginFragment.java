package com.example.b07group7project.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.b07group7project.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

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
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter(mAuth, this);

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
}