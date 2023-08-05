package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserType;
import com.example.b07group7project.database.OnComplete;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface LoginModel {
    void signIn(String email, String password, OnCompleteListener<AuthResult> onComplete);
    void register(String email, String password, OnCompleteListener<AuthResult> onComplete);
    FirebaseUser getCurrentUser();
    void sendResetLink(String email);
    void setUserType(UserType type, FirebaseUser user);

    void getUserType(FirebaseUser user, OnComplete<UserType> withUserType);

    void signOut();

}
