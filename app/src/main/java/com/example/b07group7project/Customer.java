package com.example.b07group7project;

import static java.util.UUID.fromString;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Customer {
    String email;
    String name;
    List<PreviousOrder> previousOrders;
    List<Product> shoppingCart;


    public Customer(String email, String name) {
        email = checkFor(email);
        this.name = name;
    }

    public Customer(String email, String name, List<PreviousOrder> previousOrders) {
        email = checkFor(email);

        this.previousOrders = previousOrders;
    }

    public Customer(String email, String name, List<PreviousOrder> previousOrders, List<Product> shoppingCart) {
        email = checkFor(email);
        this.name = name;
        this.previousOrders = previousOrders;
        this.shoppingCart = shoppingCart;
    }

    public HashMap<String, Object> putIntoHashMap() {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("Customer Email", this.email);
        if (previousOrders != null) {
            hashmap.put("Previous Orders", this.previousOrders);
        }
        if (shoppingCart != null) {
            hashmap.put("Shopping Cart", this.shoppingCart);
        }
        return hashmap;
    }

    private String checkFor(String email) {
        if (email.contains("@")){
            return email.substring(0, email.indexOf("@"));
        }
        return email;
    }
}
