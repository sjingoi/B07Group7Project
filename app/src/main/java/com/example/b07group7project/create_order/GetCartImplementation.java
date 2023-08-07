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

        StoreProduct[] prod ={
                new StoreProduct("FlashLight", "DESC", "URL", 9.99),
                new StoreProduct("Battery", "DESC", "URL", 12.36),
                new StoreProduct("Poster", "DESC", "URL", 9.99),
                new StoreProduct("TV", "DESC", "URL", 1299.99),
                new StoreProduct("T-Shirt", "DESC", "URL", 20.00),
                new StoreProduct("Jeans", "DESC", "URL", 50.00),
                new StoreProduct("VERY VERY LONG NAME", "DESC", "URL",1000000),
                new StoreProduct("Shorts", "DESC", "URL", 10.99),
                new StoreProduct("Battery", "DESC", "URL",12.36),
                new StoreProduct("Poster", "DESC", "URL",9.99),
                new StoreProduct("TV", "DESC", "URL",1299.99),
                new StoreProduct("T-Shirt", "DESC", "URL",20.00),
                new StoreProduct("Jeans", "DESC", "URL",50.00),
                new StoreProduct("VERY VERY VERY VERY VERY VERY LONG NAME", "DESC", "URL",1000000),
                new StoreProduct("Shorts", "DESC", "URL",10.99),
            };

        CartEntry[] prods = new CartEntry[15];

        for (int i = 0; i < prods.length; i++){
            prods[i] = new CartEntry(prod[i], "A", i);
        }

        onComplete.onComplete(new ArrayList<>(Arrays.asList(prods)));
    }
}
