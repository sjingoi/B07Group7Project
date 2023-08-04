package com.example.b07group7project.create_order;

import java.util.List;

public interface GetCartInterface {

    // TODO: Change User to appropiate Type for DataBase requirments
    public List<Product> getCart(String user);
}
