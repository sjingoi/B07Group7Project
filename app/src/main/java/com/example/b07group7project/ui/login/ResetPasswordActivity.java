package com.example.b07group7project.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07group7project.R;
import com.google.firebase.FirebaseApp;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }


    @Override
    public void onStart() {
        super.onStart();
        //TODO:
    }
}
