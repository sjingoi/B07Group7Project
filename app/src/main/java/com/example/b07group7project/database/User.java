package com.example.b07group7project.database;

import com.example.b07group7project.UserType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {
    static User user = null;

    String uuid;
    private static FirebaseUser firebaseUser;
    UserType userType;
    private User() {
        this.uuid = null;
        this.userType = null;
    }

    public static void removeCurrentUser() {
        user = null;
        firebaseUser = null;
        FirebaseAuth.getInstance().signOut();
    }

    public static User getCurrentUser(){
        if(firebaseUser == null || user == null){
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if(firebaseUser != null)
                user = new User();
        }
        return user;
    }

    public String getEmail() {
        if(firebaseUser == null)
            return null;
        return firebaseUser.getEmail();
    }
}