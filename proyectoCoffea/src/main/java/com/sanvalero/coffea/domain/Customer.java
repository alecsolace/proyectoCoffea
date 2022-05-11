package com.sanvalero.coffea.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Customer {
    private int userID = 0;
    private Address address;
    private String name;
    private String lastName;
    private String email;
    private String password;

    public Customer(Address address, String name, String lastName, String email, String password) {
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void registrarUsuario() {
        // Insertar los metodos de la base de datos
        int filas = 0;
        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");

            String createUser = "INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(createUser);

            userStatement.setInt(1, this.userID);
            userStatement.setInt(2, this.address.getAddressID());
            userStatement.setString(3, this.name);
            userStatement.setString(4, this.lastName);
            userStatement.setString(5, this.email);
            userStatement.setString(6, this.password);

            filas = userStatement.executeUpdate();
            System.out.println("Se han insertado: " + filas + " filas.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void modificarUsuario() {
        // Insertar la query de modificación.
    }

}
