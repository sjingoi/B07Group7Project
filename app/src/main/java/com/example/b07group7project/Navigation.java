package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class Navigation extends AppCompatActivity implements FragmentSwitch {


    public abstract int getFragmentContainer();
    @Override
    public void replaceFragment(Fragment newFragment, Boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(getFragmentContainer(), newFragment);
        fragmentTransaction.commit();
    }
}
