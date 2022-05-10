package main.java.com.sanvalero.coffea.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Address {
    private int address_ID = 0;
    private String streetName;
    private String streetNumber;
    private String appartment;

    public Address(String streetName, String streetNumber, String appartment) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.appartment = appartment;
        registrarAddress();
        address_ID++;
    }

    public void registrarAddress() {
        // Insertar los metodos de la base de datos
        int filas = 0;
        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");

            String createUser = "INSERT INTO ADDRESS VALUES(?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(createUser);

            userStatement.setInt(1, address_ID);
            userStatement.setString(2, streetName);
            userStatement.setString(3, streetNumber);
            userStatement.setString(4, appartment);
            filas = userStatement.executeUpdate();
            System.out.println("Se han insertado: " + filas + " filas.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public int getAddress_ID() {
        return address_ID;
    }

    public void setAddress_ID(int address_ID) {
        this.address_ID = address_ID;
    }

}
