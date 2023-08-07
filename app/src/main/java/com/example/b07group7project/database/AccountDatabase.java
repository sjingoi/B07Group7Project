package com.example.b07group7project.database;

import androidx.annotation.NonNull;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AccountDatabase implements UserPermission {

    FirebaseDatabase database;
    DatabaseReference reference;

    public AccountDatabase(){
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }

    @Override
    public void getUserType(FirebaseUser user, OnComplete<UserType> withUserType) {
        String userEmail = Objects.requireNonNull(user.getEmail());

        //This line is necessary to avoid restricted values for keys for Firebase
        String encodedEmail = Base64.getEncoder()
                .encodeToString(userEmail.getBytes(StandardCharsets.UTF_8))
                .replace('\\', ';');

        DatabaseReference databaseReference = reference.child(Constants.accounts).child(encodedEmail);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    UserType userType = snapshot.child(encodedEmail).child(Constants.user_type)
                            .getValue(UserType.class);
                    withUserType.onComplete(userType);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void createUserOfType(UserType type, @NonNull FirebaseUser user) {
        String userEmail = Objects.requireNonNull(user.getEmail());
        String userUUID = String.valueOf(UUID.randomUUID());

        //This line is necessary to avoid restricted values for keys for Firebase
        String encodedEmail = Base64.getEncoder()
                .encodeToString(userEmail.getBytes(StandardCharsets.UTF_8))
                .replace('\\', ';');

        DatabaseReference databaseReference = reference.child(Constants.accounts)
                .child(encodedEmail);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    HashMap<String, String> data = new HashMap<>();
                    data.put(Constants.user_type, type.toString());
                    data.put(Constants.user_uuid, userUUID);
                    databaseReference.setValue(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        if(type == UserType.SHOPPER){
            DatabaseReference secondDatabaseReference = reference.child(Constants.customers)
                    .child(userUUID);

            secondDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put(Constants.email, encodedEmail);
                        secondDatabaseReference.setValue(data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else if (type == UserType.STORE_OWNER) {
            DatabaseReference secondDatabaseReference = reference.child(Constants.store_owners)
                    .child(userUUID);
            String storeUUID = UUID.randomUUID().toString();

            secondDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()) {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put(Constants.email, encodedEmail);
                        data.put(Constants.store_uuid, storeUUID);
                        secondDatabaseReference.setValue(data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}
