package com.sanvalero.coffea.domain;

public class Customer {

    private int userID = 0;
    private String name;
    private String lastName;
    private String email;
    private String password;

    public Customer(String name, String lastName, String email, String password) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        userID++;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void modificarUsuario() {
        // Insertar la query de modificación.
    }

}
