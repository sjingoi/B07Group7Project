package com.example.b07group7project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button registerbutton;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerbutton = findViewById(R.id.register);
        authentication = FirebaseAuth.getInstance();
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailtext = email.getText().toString();
                String passwordtext = password.getText().toString();
                if (emailtext.equals("") || passwordtext.equals("")){
                    Toast.makeText(RegisterActivity.this, "Email and/or password cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(emailtext, passwordtext);
                }
            }
        });
    }

    private void registerUser(String emailtext, String passwordtext) {
        authentication.createUserWithEmailAndPassword(emailtext, passwordtext).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}