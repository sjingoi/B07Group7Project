package com.example.b07group7project.shopper_view_store;

import com.example.b07group7project.Store;

import java.util.ArrayList;
import java.util.List;

// This Is Class For Testing, Will Be Removed later on and will call getStores from database
public class GetStoreImplementation implements GetStoreInterface {
    public List<Store> getStores() {
        List<Store> StoreList = new ArrayList<>();
        
        String[] StoreLogos = {"https://picsum.photos/200/300", "https://picsum.photos/200/300",
                "https://picsum.photos/200/300", "https://picsum.photos/200/300",
                "https://picsum.photos/200/300", "https://picsum.photos/200/300",
                "https://picsum.photos/200/300", "https://picsum.photos/200/300",
                "https://picsum.photos/200/300"};

        String[] StoreNames = {"Walmart", "Winners", "Target", "Taco Bell", "McDonalds", "Burger King",
                "Normal Name", "Foo Locker", "Costco"};

        for (int i = 0; i < StoreNames.length; i++) {
            StoreList.add(new Store(StoreNames[i], StoreLogos[i]));
        }

        return StoreList;
    }


}