package com.example.b07group7project;

import static java.util.UUID.fromString;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

public class Customer {
    String email;
    List<PreviousOrder> previousOrders;
    FirebaseDatabase database;
    DatabaseReference reference;

    public Customer(String email) {
        this.email = email;
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
        DatabaseReference newreference = reference.child("Customers").child("Email").child("Previous Orders");
        previousOrders = PreviousOrder.getPreviousOrders(reference, email);
    }
}
