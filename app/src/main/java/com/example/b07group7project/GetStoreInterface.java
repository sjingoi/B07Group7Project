package com.example.b07group7project;

import java.util.ArrayList;
import java.util.List;

public interface GetStoreInterface {
    public static ArrayList<Store> getStores(){
        ArrayList<Store> StoreList = new ArrayList<>();
        int[] StoreLogos = {R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,};
        String[] StoreNames = {"Walmart", "Winners", "Target", "Taco Bell", "McDonalds", "Burger King",
                "Normal Name", "Foot Locker", "Costco"};

        for (int i = 0; i < StoreNames.length; i++){
            StoreList.add(new Store(StoreNames[i], StoreLogos[i]));
        }

        return StoreList;
    }
}
