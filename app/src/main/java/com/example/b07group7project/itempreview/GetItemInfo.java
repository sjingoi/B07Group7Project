package com.example.b07group7project.itempreview;
import java.util.concurrent.CompletableFuture;

public interface GetItemInfo {
    CompletableFuture<StoreItem> getItemInformation(String itemID);
}
