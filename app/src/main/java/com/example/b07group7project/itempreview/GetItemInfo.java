package com.example.b07group7project.itempreview;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

public interface GetItemInfo {
    void getItemInformation(OnComplete<StoreProduct> withStoreProduct);
}
