package com.example.b07group7project.view_products;


import com.example.b07group7project.database_abstractions.StoreProduct;

// Interface for ViewProductFragment (Not really Needed but code works kinda nice so just leave it)
public interface ProductClickListener {

    void onProductClicked(StoreProduct product);
}
