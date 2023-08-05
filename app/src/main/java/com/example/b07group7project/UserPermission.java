package com.example.b07group7project;

import com.example.b07group7project.database.OnComplete;
import com.google.firebase.auth.FirebaseUser;

public interface UserPermission {
    void getUserType(FirebaseUser user, OnComplete<UserType> withUserType);
    void createUserOfType(UserType type, FirebaseUser user);
}