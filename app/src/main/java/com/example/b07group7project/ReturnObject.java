package com.example.b07group7project;

import java.util.HashMap;
import java.util.List;

public class ReturnObject {
    HashMap<String, Object> hashmap;
    List<String> list;
    public ReturnObject(HashMap<String, Object> hashmap) {
        this.hashmap = hashmap;
    }

    public ReturnObject(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public HashMap<String, Object> getHashmap() {
        return hashmap;
    }
}
