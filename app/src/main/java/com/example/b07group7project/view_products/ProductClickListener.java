package com.example.b07group7project.view_products;


import com.example.b07group7project.database_abstractions.StoreProduct;

// Interface for ViewProductFragment
public interface ProductClickListener {

    void onProductClicked(StoreProduct product);
}
