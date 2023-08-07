package com.example.b07group7project;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database.User;

public interface UserPermission {
    void getUserType(User user, OnComplete<UserType> withUserType);
    void createUserOfType(UserType type, User user);

    void getUserUUID(User user, OnComplete<String> setUuid);
}