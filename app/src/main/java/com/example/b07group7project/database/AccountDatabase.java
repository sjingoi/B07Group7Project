package com.example.b07group7project.database;

import androidx.annotation.NonNull;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class AccountDatabase extends Database implements UserPermission {

    public AccountDatabase(){
        super();
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
                root.child(Constants.accounts).child(encodedEmail).child(Constants.user_type),
                snapshot -> {
                    UserType userType = snapshot.getValue(UserType.class);
                    user.userType = userType;
                    withUserType.onComplete(userType);
                }
        );
    }

    public void getUserEmail(String userID, OnComplete<String> withUserEmail){
        get(
                root.child(Constants.customers).child(userID).child(Constants.email),
                email -> {
                    try {
                        String decodedEmail =
                                new String(Base64.getDecoder().decode(email.getValue(String.class).replace(';', '\\')), StandardCharsets.UTF_8);
                        withUserEmail.onComplete(decodedEmail);
                    }catch (Exception ignored){

                    }




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

        put(root.child(Constants.accounts).child(encodedEmail),
                snapshot ->
                {
                    HashMap<String, String> data = new HashMap<>();
                    data.put(Constants.user_type, type.toString());
                    data.put(Constants.user_uuid, userUUID);
                    return data;
                }
        );

        if(type == UserType.SHOPPER){
            put(
                    root.child(Constants.customers).child(userUUID),
                    snapshot -> {
                        HashMap<String, Object> data = new HashMap<>();
                        data.put(Constants.email, encodedEmail);
                        return data;
                    }
            );
        } else if (type == UserType.STORE_OWNER) {
            String storeUUID = UUID.randomUUID().toString();
            put(
                    root.child(Constants.store_owners).child(userUUID),
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
                root.child(Constants.accounts).child(encodedEmail).child(Constants.user_uuid),
                snapshot -> {
                    String userUUID = snapshot.getValue(String.class);
                    user.uuid = userUUID;
                    withUUID.onComplete(userUUID);
                }
        );
    }
    //Assume User is a StoreOwner
    public void getStoreUUID(User user, OnComplete<String> withUUID) {
        getUserUUID(user, uuid -> onReceiveUserUUID(withUUID, uuid));
    }

    public void onReceiveUserUUID(OnComplete<String> withUUID, String storeOwnerUUID) {
        get(root.child(Constants.store_owners).child(storeOwnerUUID),
                snapshot -> {
            String storeUUID = (String) snapshot.child(Constants.store_uuid).getValue();
            withUUID.onComplete(storeUUID);
        });
    }
    public void getStoreName(String storeUUID, OnComplete<String> withStoreName) {
        get(
                root.child(Constants.store_owners).child(storeUUID).child(Constants.store_uuid),
                snapshot -> {
                    String storeName = snapshot.getValue(String.class);
                    withStoreName.onComplete(storeName);
                }
        );
    }
}
