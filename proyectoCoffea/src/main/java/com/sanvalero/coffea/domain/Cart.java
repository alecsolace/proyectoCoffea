package com.sanvalero.coffea.domain;

import java.sql.Date;

public class Cart {

    private int cartID;
    private Customer customer;
    private double price;
    private Date orderDate;

    public Cart(int cartID, Customer customer, double price, Date orderDate) {
        this.cartID = cartID;
        this.customer = customer;
        this.price = price;
        this.orderDate = orderDate;
    }

    public Cart() {
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
