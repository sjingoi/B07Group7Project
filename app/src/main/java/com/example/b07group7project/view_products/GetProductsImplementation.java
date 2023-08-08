package com.example.b07group7project.view_products;


import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;



import java.util.ArrayList;
import java.util.Arrays;

// Sample Implementation to be swapped when actually implementing
public class GetProductsImplementation implements GetProductsInterface {

    @Override
    public void getProducts(String storeID, OnComplete<ArrayList<StoreProduct>> onComplete) {

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

        onComplete.onComplete(new ArrayList<>(Arrays.asList(products)));
    }
}
