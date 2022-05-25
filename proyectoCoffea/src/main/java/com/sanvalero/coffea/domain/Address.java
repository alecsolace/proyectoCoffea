package com.sanvalero.coffea.domain;

public class Address {

    private int addressID = 0;
    private Customer customer;
    private String streetName;
    private int streetNumber;
    private String appartment;

    public Address(Customer customer, String streetName, int streetNumber, String appartment) {
        this.customer = customer;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.appartment = appartment;
        addressID++;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int address_ID) {
        this.addressID = address_ID;
    }

    public String getAppartment() {
        return appartment;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
