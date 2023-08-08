package com.example.b07group7project;

import java.util.List;
import java.util.UUID;

public class Customer {
    String email;
    String name;
    List<Product> shoppingCart;
    String uuid;

    public Customer(String email, String name) {
        //email = checkFor(email);
        this.email = email;
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
    }



    public Customer(String email, String name, List<Product> shoppingCart) {
        //email = checkFor(email);
        this.email = email;
        this.name = name;
        this.shoppingCart = shoppingCart;
        this.uuid = UUID.randomUUID().toString();
    }

}
