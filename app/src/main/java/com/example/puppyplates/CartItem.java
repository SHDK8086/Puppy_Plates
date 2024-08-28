package com.example.puppyplates;

public class CartItem {
    private String name;
    private double price;
    private int quantity;

    // Constructor to initialize a CartItem object
    public CartItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
