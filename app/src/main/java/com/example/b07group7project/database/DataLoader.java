package com.example.b07group7project.database;

import com.google.firebase.database.DataSnapshot;

public interface DataLoader<T> {
    T load(DataSnapshot s);
}
