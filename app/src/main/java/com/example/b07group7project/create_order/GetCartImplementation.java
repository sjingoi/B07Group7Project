package com.example.b07group7project.create_order;

import java.util.ArrayList;
import java.util.List;

// Example Class Only For Testing, Will be replaced with Database Call (or from cart)
public class GetCartImplementation implements GetCartInterface{

    @Override
    public List<Product> getCart(String user) {
        ArrayList<Product> products = new ArrayList<Product>();

        for (int i = 0; i < 15; i++){
            Product prod = new Product("FlashLight", 9.99, 5);
            products.add(prod);
        }

        return products;
    }
}
