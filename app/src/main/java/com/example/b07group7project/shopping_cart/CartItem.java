package com.example.b07group7project.shopping_cart;
import android.view.View;

public class CartItem {

    String productName;
    int quantity;
    String imageURL;

    View.OnClickListener onClickListener;

    public CartItem(String productName, int quantity, String imageURL, View.OnClickListener onClickListener) {
        this.productName = productName;
        this.quantity = quantity;
        this.imageURL = imageURL;

        this.onClickListener = onClickListener;

    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImage() {
        return imageURL;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(String imageURL) {
        this.imageURL = imageURL;
    }

}
