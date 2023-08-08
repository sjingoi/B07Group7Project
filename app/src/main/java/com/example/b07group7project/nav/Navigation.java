package com.example.b07group7project.nav;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.b07group7project.R;

public abstract class Navigation extends AppCompatActivity {


    public abstract int getFragmentContainer();

    public void replaceFragment(Fragment newFragment, boolean addToBackStack, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
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
