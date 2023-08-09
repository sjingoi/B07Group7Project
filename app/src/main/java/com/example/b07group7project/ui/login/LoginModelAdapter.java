package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;
import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModelAdapter implements LoginModel{
    FirebaseAuth mAuth;
    UserPermission userPermission;
    public LoginModelAdapter(FirebaseAuth mAuth, UserPermission userPermission){
        this.mAuth = mAuth;
        this.userPermission = userPermission;
    }

    @Override
    public void signIn(String email, String password, OnCompleteListener<AuthResult> onComplete) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onComplete);
    }

    @Override
    public void register(String email, String password, OnCompleteListener<AuthResult> onComplete) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onComplete);
    }

    @Override
    public User getCurrentUser() {
        return User.getCurrentUser();
    }

    @Override
    public void sendResetLink(String email) {
        mAuth.sendPasswordResetEmail(email);
    }

    @Override
    public void setUserType(UserType type, User user) {
        userPermission.createUserOfType(type, user);
    }

    @Override
    public void getUserType(User user, OnComplete<UserType> withUserType) {
        userPermission.getUserType(user, withUserType);
    }

    @Override
    public void signOut() {
        mAuth.signOut();
        User.removeCurrentUser();
    }
}
