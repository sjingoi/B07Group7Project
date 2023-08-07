package com.example.b07group7project;

import com.example.b07group7project.database.Constants;

import java.util.HashMap;
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

    public HashMap<String, Object> putIntoHashMap() {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put(Constants.customer_email, this.email);
        hashmap.put(Constants.customer_name, this.name);
        if (shoppingCart != null) {
            hashmap.put(Constants.shopping_cart, this.shoppingCart);
        }
        else {
            hashmap.put(Constants.shopping_cart, "");
        }
        return hashmap;
    }

    public HashMap<String, String> putIntoShoppingMap(Product product, int quantity) {
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put(Constants.store_uuid, product.storeuuid);
        hashmap.put(Constants.quantity, Integer.toString(quantity));

        return hashmap;
    }

    private String checkFor(String email) {
        if (email.contains("@")){
            return email.substring(0, email.indexOf("@"));
        }
        return email;
    }
}
