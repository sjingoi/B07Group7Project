package com.example.b07group7project;

import com.google.firebase.auth.FirebaseUser;

//TODO: implement database methods for this interface
public interface UserPermission {
    UserType getUserType(FirebaseUser user);
    void createUserOfType(UserType type, FirebaseUser user);
}