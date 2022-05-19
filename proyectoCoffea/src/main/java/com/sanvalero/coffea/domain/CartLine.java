package com.sanvalero.coffea.domain;

public class CartLine {

    private int cartLineID = 0;
    private Product product;
    private Cart cart;
    private int quantity;
    private double price;

    public CartLine(int cartLineID, Product product, Cart cart, int quantity, double price) {
        this.cartLineID = cartLineID;
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.price = price;
    }

    public CartLine(int cartLineID, Product product, int quantity, double price) {
        this.cartLineID = cartLineID;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getCartLineID() {
        return cartLineID;
    }

    public void setCartLineID(int cartLineID) {
        this.cartLineID = cartLineID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
