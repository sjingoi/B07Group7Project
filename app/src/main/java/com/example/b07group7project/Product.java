package com.example.b07group7project;

import java.util.HashMap;

public class Product {

    String productname;
    String description;
    String price;


    public Product(String productname, String description, String price){
        this.productname = productname;
        this.description = description;
        this.price = price;
    }
    public Product(String productname, String description, double price){
        this.productname = productname;
        this.description = description;
        Double d = price;
        this.price = d.toString();
    }
    public String getProductname() {
        return productname;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public HashMap<String, Object> putIntoHashMap(){
        HashMap<String, Object> product = new HashMap<>();
        product.put("Product name", productname);
        product.put("Product Description", description);
        product.put("Product Price", price);
        return product;
    }
}
