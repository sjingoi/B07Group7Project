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
        if(user == null){
            this.uuid = null;
            this.userType = null;
        }
        else {
            AccountDatabase db = new AccountDatabase();
            db.getUserUUID(this, this::setUuid);
            db.getUserType(this, this::setUserType);
        }
    }

    private void setUserType(UserType userType) {
        this.userType = userType;
    }

    public static void removeCurrentUser() {
        user = null;
        firebaseUser = null;
        FirebaseAuth.getInstance().signOut();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static User getCurrentUser(){
        if(firebaseUser == null || user == null){
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            user = new User();
        }
        return user;
    }

    public String getEmail() {
        if(firebaseUser == null)
            return null;
        return firebaseUser.getEmail();
    }
    public UserType getUserType(){return userType;}
}