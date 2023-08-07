package com.example.b07group7project.database;

import androidx.annotation.NonNull;

import com.example.b07group7project.Constants;
import com.example.b07group7project.database_abstractions.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountDatabase {

    FirebaseDatabase database;
    DatabaseReference reference;

    public AccountDatabase(){
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }

    public void addAccount(Account account) {
        DatabaseReference newreference = reference.child(Constants.accounts).child(account.getEmail());
        newreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    newreference.setValue(account.getAccounttype());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
