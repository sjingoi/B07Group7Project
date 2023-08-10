package com.example.b07group7project.nav;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.b07group7project.R;

public abstract class Navigation extends AppCompatActivity {


    public abstract int getFragmentContainer();

    public void replaceFragment(Fragment newFragment, boolean addToBackStack, Bundle bundle, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
        }
        if (bundle != null) {
            newFragment.setArguments(bundle);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && title != null) {
            //actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
        fragmentTransaction.replace(getFragmentContainer(), newFragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment newFragment, boolean addToBackStack, Bundle bundle) {
        replaceFragment(newFragment, addToBackStack, bundle, null);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if (addToBackStack) {
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left, R.anim.enter_left_to_right, R.anim.exit_left_to_right);
//        }
//        if (bundle != null) {
//            newFragment.setArguments(bundle);
//        }
//        fragmentTransaction.replace(getFragmentContainer(), newFragment);
//        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment newFragment, boolean addToBackStack, String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
        }
        replaceFragment(newFragment, addToBackStack);
    }

    public void replaceFragment(Fragment newFragment, boolean addToBackStack) {
        replaceFragment(newFragment, addToBackStack, (Bundle) null);
    }

    public void replaceFragment(Fragment newFragment) {
        replaceFragment(newFragment, false);
    }

    protected void setUpToolbar() {
        Toolbar toolbar = getToolbar();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Home");
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public abstract Toolbar getToolbar();

    public void setSystemBarColors() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.nav_background_color));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.nav_background_color));
    }

//
//    public void replaceFragment(Fragment newFragment) {
//        replaceFragment(newFragment, false);
//    }
}
