package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserType;
import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;


public interface LoginModel {
    void signIn(String email, String password, OnCompleteListener<AuthResult> onComplete);
    void register(String email, String password, OnCompleteListener<AuthResult> onComplete);
    User getCurrentUser();
    void sendResetLink(String email);
    void setUserType(UserType type, User user);

    void getUserType(User user, OnComplete<UserType> withUserType);

    void signOut();

}
