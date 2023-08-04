package com.example.b07group7project.create_order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Example Class Only For Testing, Will be replaced with Database Call (or from cart)
public class GetCartImplementation implements GetCartInterface{

    @Override
    public ArrayList<Product> getCart(String user) {

        Product[] prods ={
            new Product("FlashLight", 9.99, 5),
                    new Product("Battery", 12.36, 2),
                    new Product("Poster", 9.99, 1),
                    new Product("TV", 1299.99, 1),
                    new Product("T-Shirt", 20.00, 3),
                    new Product("Jeans", 50.00, 1),
                    new Product("VERY VERY LONG NAME", 1000000, 1),
                    new Product("Shorts", 10.99, 10),
                    new Product("Battery", 12.36, 2),
                    new Product("Poster", 9.99, 1),
                    new Product("TV", 1299.99, 1),
                    new Product("T-Shirt", 20.00, 3),
                    new Product("Jeans", 50.00, 1),
                    new Product("VERY VERY VERY VERY VERY VERY LONG NAME", 1000000, 1),
                    new Product("Shorts", 10.99, 10),
            };


        return new ArrayList<>(Arrays.asList(prods));
    }
}
