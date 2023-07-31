package com.example.b07group7project.ui.login;

import com.example.b07group7project.UserPermission;
import com.example.b07group7project.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    @Override
    public void sendResetLink(String email) {
        mAuth.sendPasswordResetEmail(email);
    }

    @Override
    public void setUserType(UserType type, FirebaseUser user) {
        userPermission.createUserOfType(type, user);
    }

    @Override
    public UserType getUserType(FirebaseUser user) {
        return userPermission.getUserType(user);
    }

    @Override
    public void signOut() {
        mAuth.signOut();
    }
}
