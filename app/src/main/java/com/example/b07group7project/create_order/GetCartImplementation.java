package com.example.b07group7project.create_order;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopping_cart.CartEntry;

import java.util.ArrayList;
import java.util.Arrays;


// Example Class Only For Testing, Will be replaced with Database Call (or from cart)
public class GetCartImplementation implements GetCartInterface{

    @Override
    public void getCart(OnComplete<ArrayList<CartEntry>> onComplete){

        StoreProduct[] products = {
                new StoreProduct("FlashLight", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 9.99),
                new StoreProduct("Battery", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 12.36),
                new StoreProduct("Poster", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 9.99),
                new StoreProduct("TV", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 1299.99),
                new StoreProduct("T-Shirt", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 20.00),
                new StoreProduct("Jeans", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 50.00),
                new StoreProduct("VERY VERY LONG NAME", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 1000000),
                new StoreProduct("Shorts", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 10.99),
                new StoreProduct("Battery", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 12.36),
                new StoreProduct("Poster", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 9.99),
                new StoreProduct("TV", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 1299.99),
                new StoreProduct("T-Shirt", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 20.00),
                new StoreProduct("Jeans", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 50.00),
                new StoreProduct("VERY VERY VERY VERY VERY VERY LONG NAME", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 1000000),
                new StoreProduct("Shorts", "uuid101", "uuid1", "DESC", "https://picsum.photos/200", 10.99),
        };

        CartEntry[] prods = new CartEntry[15];

        for (int i = 0; i < prods.length; i++){
            prods[i] = new CartEntry(products[i], products[i].getStoreID(), (i*97) % 7);
        }

        onComplete.onComplete(new ArrayList<>(Arrays.asList(prods)));
    }
}
