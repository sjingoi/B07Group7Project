package com.example.b07group7project.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07group7project.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this.getApplicationContext());
        setContentView(R.layout.activity_shopper_email_login);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            onLoginComplete(currentUser);
        }
    }

    public void resetPassword(){
        //TODO:
        Intent myIntent = new Intent(this, ResetPasswordActivity.class);
        startActivity(myIntent);
    }

    public void onLoginComplete(FirebaseUser user) {
        if(user == null){
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Login Succeeded", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegistrationComplete(FirebaseUser user){
        if(user == null){
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Registration Succeeded", Toast.LENGTH_SHORT).show();
        }
    }
}