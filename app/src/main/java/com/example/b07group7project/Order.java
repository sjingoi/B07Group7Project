package com.example.b07group7project;

public class Order {
    private String orderId;
    private String customerId;
    private String productName;
    private int quantity;
    private String status;

    // Constructor
    public Order(String orderId, String customerId, String productName, int quantity, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productName = productName;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}