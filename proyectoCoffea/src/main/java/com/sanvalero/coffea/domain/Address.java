package com.sanvalero.coffea.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Address {
    private int addressID = 0;
    private String streetName;
    private int streetNumber;
    private String appartment;

    public Address(String streetName, int streetNumber, String appartment) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.appartment = appartment;
        addressID++;
    }

    public void registrarAddress() {
        // Insertar los metodos de la base de datos
        int filas = 0;
        try {
            Class.forName("com.oracle.database.jdbc");
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            Connection connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");

            String createUser = "INSERT INTO ADDRESS(ADDRESS_ID, STREET_NAME, STREET_NUMBER, APPARTMENT) VALUES(?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(createUser);

            userStatement.setInt(1, addressID);
            userStatement.setString(2, streetName);
            userStatement.setInt(3, streetNumber);
            userStatement.setString(4, appartment);
            filas = userStatement.executeUpdate();
            System.out.println("Se han insertado: " + filas + " filas.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int address_ID) {
        this.addressID = address_ID;
    }

}
