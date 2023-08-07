package com.example.b07group7project.shopping_cart;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;
import java.util.List;

public class GetCartEntriesImplementation implements GetCartEntries{

    @Override
    public void getCartEntries(OnComplete<List<CartEntry>> onComplete) {
        List<CartEntry> cart = new ArrayList<>();


        StoreProduct sp = new StoreProduct("Test Product", "hi", "https://q.utoronto.ca/images/thumbnails/14520646/dZVwZaqetGv2CWPzGMdYLTsSiYz6IINFM81wJbhz", "a", 69.99);
        cart.add(new CartEntry(sp, null, 2));
        cart.add(new CartEntry(sp, null, 2));
        cart.add(new CartEntry(sp, null, 2));

        onComplete.onComplete(cart);
    }
}
