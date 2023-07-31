package com.example.b07group7project;

import com.google.firebase.auth.FirebaseUser;

public interface UserPermission {
    UserType getUserType(FirebaseUser user);
    void createUserOfType(UserType type, FirebaseUser user);
}
