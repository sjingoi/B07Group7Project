package com.example.b07group7project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CustomerDatabase {
    FirebaseDatabase database;
    DatabaseReference reference;

    public CustomerDatabase() {
        reference = database.getReference();
    }

    public void addPreviousOrder(){

    }


}
