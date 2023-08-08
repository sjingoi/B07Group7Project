package com.example.b07group7project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b07group7project.nav.NavigationFragment;

public class ExampleFragment extends NavigationFragment {

    public ExampleFragment() {
        // Required empty public constructor
    }

    public static ExampleFragment newInstance() {
        Bundle args = new Bundle();
        ExampleFragment fragment = new ExampleFragment();
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
        return inflater.inflate(R.layout.fragment_example, container, false);
    }
}