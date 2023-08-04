package com.example.b07group7project;

import com.example.b07group7project.database.OnComplete;

import java.util.ArrayList;


public interface GetStoreInterface {
    void getStores(OnComplete<ArrayList<Store>> onComplete);
}
