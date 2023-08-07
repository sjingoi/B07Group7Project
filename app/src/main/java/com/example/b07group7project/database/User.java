package com.example.b07group7project.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User {
    static User user = null;

    String uuid;
    String email;
    public User(FirebaseUser currentUser) {
        if(user == null){
            this.uuid = null;
            this.email = null;
        }
        else {
            email = currentUser.getEmail();
            AccountDatabase db = new AccountDatabase();
            db.getUserUUID(this, this::setUuid);
        }
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static User getCurrentUser(){
        if(user == null){
            user = new User(FirebaseAuth.getInstance().getCurrentUser());
        }
        return user;
    }

    public String getEmail() {
        return email;
    }
}
