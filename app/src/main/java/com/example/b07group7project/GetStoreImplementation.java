package com.example.b07group7project;

import java.util.ArrayList;
import java.util.List;

// This Is Class For Testing, Will Be Removed later on and will call getStores from database
public class GetStoreImplementation implements GetStoreInterface{
    public ArrayList<Store> getStores() {
        ArrayList<Store> StoreList = new ArrayList<>();
        int[] StoreLogos = {R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,
                R.drawable.baseline_logo1_24, R.drawable.baseline_logo1_24,};
        String[] StoreNames = {"Walmart", "Winners", "Target", "Taco Bell", "McDonalds", "Burger King",
                "Normal Name", "Foo Locker", "Costco"};

        for (int i = 0; i < StoreNames.length; i++) {
            StoreList.add(new Store(StoreNames[i], StoreLogos[i]));
        }

        return StoreList;
    }


}