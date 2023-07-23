package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button registerbutton;
    private Button loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        registerbutton = findViewById(R.id.register);
        loginbutton = findViewById(R.id.login);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(StartActivity.this, RegisterActivity.class));
                finish();
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}