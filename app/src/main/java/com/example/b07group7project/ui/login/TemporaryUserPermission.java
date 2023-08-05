package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;
import com.example.b07group7project.database.OnComplete;
import com.google.firebase.auth.FirebaseUser;

public class TemporaryUserPermission implements UserPermission {

    @Override
    public void getUserType(FirebaseUser user, OnComplete<UserType> withUserType) {
        withUserType.onComplete(UserType.SHOPPER);
    }

    @Override
    public void createUserOfType(UserType type, FirebaseUser user) {
    }
}
