package com.example.b07group7project.database;

import com.google.firebase.database.DataSnapshot;

public interface DataSetter<T> {
    T getData(DataSnapshot snapshot);
}
