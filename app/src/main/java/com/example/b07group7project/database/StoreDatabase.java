package com.example.b07group7project.database;

import com.example.b07group7project.create_store.SaveStore;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.shopper_view_store.GetStoreInterface;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreDatabase extends Database implements GetStoreInterface, SaveStore {
    ArrayList<Store> stores;


    public StoreDatabase(){
        super();
    }
    public void getStores(OnComplete<ArrayList<Store>> onComplete) {
        if(stores != null){
            onComplete.onComplete(new ArrayList<>(stores));
            return;
        }

        getWithCache(
                root.child(Constants.stores),
                onComplete,
                this::updateStoreList
        );
    }

    public ArrayList<Store> updateStoreList(DataSnapshot dataSnapshot){
        ArrayList<Store> store = new ArrayList<>();
        for (DataSnapshot e : dataSnapshot.getChildren()) {
            String name = e.child(Constants.store_name).getValue(String.class);
            String url = e.child(Constants.store_image).getValue(String.class);
            String uuid = e.getKey();

            store.add(new Store(name, uuid, url));
        }
        stores = new ArrayList<>(store);
        return store;
    }

    @Override
    public void saveStoreToFirebase(String storeName, String storeDesc, String imageURL) {
        User user = User.getCurrentUser();
        AccountDatabase db = new AccountDatabase();
        db.getUserUUID(
                user,
                userUUID -> get(
                        root.child(Constants.store_owners).child(userUUID),
                        snapshot -> {
                            String storeUUID = snapshot.child(Constants.store_uuid).getValue(String.class);
                            if(storeUUID == null)
                                return;
                            put(
                                    root.child(Constants.stores).child(storeUUID),
                                    s -> {
                                        HashMap<String, String> storeData = new HashMap<>();
                                        storeData.put(Constants.store_name, storeName);
                                        storeData.put(Constants.store_image, imageURL);
                                        storeData.put(Constants.store_description, storeDesc);
                                        return storeData;
                                    }
                            );
                        }
                )
        );

    }

}
