package com.example.b07group7project.nav;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class Navigation extends AppCompatActivity {


    public abstract int getFragmentContainer();

    public void replaceFragment(Fragment newFragment, boolean addToBackStack, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        if (bundle != null) {

        }
        fragmentTransaction.replace(getFragmentContainer(), newFragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
        replaceFragment(newFragment, addToBackStack, null);
    }

    public void replaceFragment(Fragment newFragment) {
        replaceFragment(newFragment, false, null);
    }
}
