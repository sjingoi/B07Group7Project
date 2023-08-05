package com.example.b07group7project.shopper_view_store;

import com.example.b07group7project.Store;
import com.example.b07group7project.database.OnComplete;

import java.util.ArrayList;


public interface GetStoreInterface {
    void getStores(OnComplete<ArrayList<Store>> withStoreList);
}
