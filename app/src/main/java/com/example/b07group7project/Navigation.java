package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class Navigation extends AppCompatActivity {


    public abstract int getFragmentContainer();

    public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(getFragmentContainer(), newFragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment newFragment) {
        replaceFragment(newFragment, false);
    }
}
