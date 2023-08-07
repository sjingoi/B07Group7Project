package com.example.b07group7project.database;

import androidx.annotation.NonNull;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AccountDatabase extends Database implements UserPermission {

    FirebaseDatabase database;
    DatabaseReference reference;

    public AccountDatabase(){
        database = FirebaseDatabase.getInstance(Constants.database_url);
        reference = database.getReference();
    }

    @Override
    public void getUserType(User user, OnComplete<UserType> withUserType) {
        if(user.userType != null){
            withUserType.onComplete(user.userType);
            return;
        }

        String userEmail = user.getEmail();
        if(userEmail == null)
            return;

        //This line is necessary to avoid restricted values for keys for Firebase
        String encodedEmail = Base64.getEncoder()
                .encodeToString(userEmail.getBytes(StandardCharsets.UTF_8))
                .replace('\\', ';');

        get(
                reference.child(Constants.accounts).child(encodedEmail),
                snapshot -> {
                    UserType userType = snapshot.child(encodedEmail).child(Constants.user_type)
                            .getValue(UserType.class);
                    user.userType = userType;
                    withUserType.onComplete(userType);
                }
        );
    }

    @Override
    public void createUserOfType(UserType type, @NonNull User user) {
        String userEmail = Objects.requireNonNull(user.getEmail());
        String userUUID = String.valueOf(UUID.randomUUID());

        //This line is necessary to avoid restricted values for keys for Firebase
        String encodedEmail = Base64.getEncoder()
                .encodeToString(userEmail.getBytes(StandardCharsets.UTF_8))
                .replace('\\', ';');

        put(
                reference.child(Constants.accounts).child(encodedEmail),
                snapshot -> {
                    HashMap<String, String> data = new HashMap<>();
                    data.put(Constants.user_type, type.toString());
                    data.put(Constants.user_uuid, userUUID);
                    return data;
                }
        );

        if(type == UserType.SHOPPER){
            put(
                    reference.child(Constants.customers).child(userUUID),
                    snapshot -> {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put(Constants.email, encodedEmail);
                        return data;
                    }
            );
        } else if (type == UserType.STORE_OWNER) {
            String storeUUID = UUID.randomUUID().toString();
            put(
                    reference.child(Constants.store_owners).child(userUUID),
                    snapshot -> {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put(Constants.email, encodedEmail);
                        data.put(Constants.store_uuid, storeUUID);
                        return data;
                    }
            );
        }

    }

    @Override
    public void getUserUUID(User user, OnComplete<String> withUUID) {
        if(user.uuid != null){
            withUUID.onComplete(user.uuid);
            return;
        }

        String userEmail = user.getEmail();
        if(userEmail == null)
            return;

        //This line is necessary to avoid restricted values for keys for Firebase
        String encodedEmail = Base64.getEncoder()
                .encodeToString(userEmail.getBytes(StandardCharsets.UTF_8))
                .replace('\\', ';');

        get(
                reference.child(Constants.accounts).child(encodedEmail),
                snapshot -> {
                    String userUUID = snapshot.child(encodedEmail).child(Constants.user_type)
                            .getValue(String.class);
                    user.uuid = userUUID;
                    withUUID.onComplete(userUUID);
                }
        );
    }
}
