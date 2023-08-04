package com.example.b07group7project.create_order;

import java.util.ArrayList;

public interface GetCartInterface {

    // TODO: Change User to appropiate Type for DataBase requirments
    public ArrayList<Product> getCart(String user);
}
