package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;
import com.google.firebase.auth.FirebaseUser;

public class TemporaryUserPermission implements UserPermission {

    @Override
    public UserType getUserType(FirebaseUser user) {
        return UserType.SHOPPER;
    }

    @Override
    public void createUserOfType(UserType type, FirebaseUser user) {
    }
}
