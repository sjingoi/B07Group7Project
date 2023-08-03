package com.example.b07group7project;

import java.util.HashMap;
import java.util.UUID;

public class Product {

    String productname;
    String description;
    String price;
    String imageURL;
    String uuid;

    public Product(String productname, String description, String price, String imageURL){
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
        this.uuid = UUID.randomUUID().toString();
    }
    public Product(String productname, String description, double price, String imageURL){
        this.productname = productname;
        this.description = description;
        this.price = Double.toString(price);
        this.imageURL = imageURL;
        this.uuid = UUID.randomUUID().toString();
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
        product.put("Product imageURL", imageURL);
        return product;
    }
}
