package com.example.b07group7project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private Button registerbutton;
    private Button loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        registerbutton = findViewById(R.id.register);
        loginbutton = findViewById(R.id.login);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com").getReference();
        StoreDatabase s1 = new StoreDatabase();
        AccountDatabase ad = new AccountDatabase();

        /*
        ArrayList<String> list = new ArrayList<>();
        s1.getStores(list);
        for (String s : list) {
            System.out.println(s);
        }

        HashMap<String, Object> hashmap = new HashMap<>();
        s1.getProductsForStore(hashmap, "uuid1");
         */



        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //Store store = new Store("Walmart", "Walmart is a Cool Store", "walmart@gmail.com", "walmartimageURL");
                //s1.addStore(store);
                //Store store2 = new Store("Cooldrop", "Cooldrop is a Cool Store", "cool.drop@gmail.com", "coolimageURL");
                //s1.addStore(store2);
                Product p1 = new Product("Chocolate", "Tasty stuff", 40, "someimageURL");
                //s1.addProductToStore(p1, "d378554b-ea38-4ddf-9c87-9891d4a847a9");
                Product p2 = new Product("Cooldrop", "Cool stuff", 20, "someimageURL2");
                //s1.addProductToStore(p2, "d378554b-ea38-4ddf-9c87-9891d4a847a9");
                Product p3 = new Product("Horse", "Cool horse", 200, "horseimageURL");
                //s1.addProductToStore(p3, "d378554b-ea38-4ddf-9c87-9891d4a847a9");
                List<String> order = new ArrayList<>();
                Product p4 = new Product("Sebastian", "Cool dude", 0, "jingoi");

                //s1.addProductToStore(p4, "d378554b-ea38-4ddf-9c87-9891d4a847a9");
                order.add("cae0458b-81cb-481f-be8c-fc47232585a7");
                order.add("13db603a-b7a9-4d5f-a30e-dfa5d6e8c04f");
                order.add("4805c494-6e57-4633-8f7f-7ee3dd440a85");
                order.add("700393f7-195a-4d62-b87b-974bbab4d1c8");
                //Customer c = new Customer("user@gmail.com", order);
                //s1.addOrderToStore(order, "d378554b-ea38-4ddf-9c87-9891d4a847a9", "uuid4");
                //Account account = new Account("abc@gmail.com", "Store Owner");
                //ad.addAccount(account);
                Toast.makeText(StartActivity.this, "", Toast.LENGTH_SHORT).show();
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