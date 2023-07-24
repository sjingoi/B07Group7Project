package com.example.b07group7project.shopping_cart;

public class CartItem {
    String productName;
    int quantity;
    int image;

    public CartItem(String productName, int quantity, int image) {
        this.productName = productName;
        this.quantity = quantity;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImage() {
        return image;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
