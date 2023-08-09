package com.example.b07group7project.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.R;
import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.shopper_view_previous_orders.ShopperPreviousOrderFragment;

public class ShopperAccountFragment extends Fragment {

    public static ShopperAccountFragment newInstance() {
        ShopperAccountFragment fragment = new ShopperAccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopper_account, container, false);

        Button signOutButton = view.findViewById(R.id.SignOutButton);
        Button viewOrdersButton = view.findViewById(R.id.ViewOrderButton);

        signOutButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), com.example.b07group7project.ui.login.EmailPasswordActivity.class);
            requireContext().startActivity(intent);
            ((Activity)requireContext()).finish();
        });

        viewOrdersButton.setOnClickListener(v -> {
            if (requireActivity() instanceof Navigation) {
                Navigation nav = (Navigation) requireActivity();
                nav.replaceFragment(ShopperPreviousOrderFragment.newInstance(), true);
            }
            //Toast.makeText(requireContext(), "Viewing Orders Now", Toast.LENGTH_LONG).show();
        });

        return view;
    }
}
