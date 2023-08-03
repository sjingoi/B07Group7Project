package com.example.b07group7project;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountDatabase {

    FirebaseDatabase database;
    DatabaseReference reference;

    public AccountDatabase(){
        database = FirebaseDatabase.getInstance("https://b07group7project-default-rtdb.firebaseio.com/");
        reference = database.getReference();
    }

    public void addAccount(Account account) {
        DatabaseReference newreference = reference.child("Accounts").child(account.email);
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    newreference.setValue(account.accounttype);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    /*
    public void getAccountType(String email) {
        Account a = new Account();
        email = a.upTo(email);

    }
    */

}
