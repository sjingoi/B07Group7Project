package com.example.b07group7project.itempreview;

import com.example.b07group7project.database.OnComplete;

public interface GetItemInfo {
    void getItemInformation(OnComplete<StoreItem> withStoreItem);
}
