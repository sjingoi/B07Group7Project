package com.example.b07group7project.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.b07group7project.R;
import com.example.b07group7project.create_store.CreateStoreFragment;
import com.example.b07group7project.nav.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreOwnerAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreOwnerAccountFragment extends Fragment {

    public StoreOwnerAccountFragment() {
        // Required empty public constructor
    }

    public static StoreOwnerAccountFragment newInstance() {
        StoreOwnerAccountFragment fragment = new StoreOwnerAccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_owner_account, container, false);

        Button signOutButton = view.findViewById(R.id.SignOutButton);
        Button editStore = view.findViewById(R.id.editStoreDetails);

        signOutButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), com.example.b07group7project.ui.login.EmailPasswordActivity.class);
            requireContext().startActivity(intent);
            ((Activity)requireContext()).finish();
        });

        editStore.setOnClickListener(v -> {
            if (requireActivity() instanceof Navigation) {
                Navigation nav = (Navigation) requireActivity();
                nav.replaceFragment(CreateStoreFragment.newInstance(), true);
            }
            //Toast.makeText(requireContext(), "Viewing Orders Now", Toast.LENGTH_LONG).show();
        });

        return view;
    }
}