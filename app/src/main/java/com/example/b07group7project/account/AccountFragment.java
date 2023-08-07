package com.example.b07group7project.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.R;


public class AccountFragment extends Fragment {

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.account_fragment, container, false);

        Button signOutButton = view.findViewById(R.id.SignOutButton);
        Button viewOrdersButton = view.findViewById(R.id.ViewOrderButton);

        signOutButton.setOnClickListener(v -> {
            // TODO: SIGN OUT WHEN BUTTON IS CLICKED
            Toast.makeText(requireContext(), "Signed Out", Toast.LENGTH_LONG).show();
        });

        viewOrdersButton.setOnClickListener(v -> {
            // TODO: Go To Orders When Button Is Clicked
            Toast.makeText(requireContext(), "Viewing Orders Now", Toast.LENGTH_LONG).show();
        });

        return view;
    }
}
