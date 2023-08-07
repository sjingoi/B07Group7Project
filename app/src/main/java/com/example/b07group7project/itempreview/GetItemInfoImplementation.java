package com.example.b07group7project.itempreview;

import android.os.Handler;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

public class GetItemInfoImplementation implements GetItemInfo {
   @Override
   public void getItemInformation(OnComplete<StoreProduct> onComplete) {
      // Create a dummy StoreProduct with mock information
      StoreProduct mockItem = new StoreProduct("Item", "Desc", "url", "a", 0);
      mockItem.setItemName("Dummy Item");
      mockItem.setPrice(10.99);
      mockItem.setDescription("This is a mock item for testing purposes.");

      // Simulate an asynchronous callback by using a Handler with a delay
      new Handler().postDelayed(() -> {
         onComplete.onComplete(mockItem);
      }, 1000); // 1000 milliseconds delay (1 second)
   }
}

