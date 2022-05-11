package com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanvalero.coffea.domain.Address;

public class AddressDAO {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Address> addresses;

    private Connection connection;

    public AddressDAO() throws SQLException {
        connect();
        addresses = getAddresses();
    }

    /**
     * Conecta con la base de datos
     */
    public void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_CONEXION, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public ArrayList<Address> getAddresses() throws SQLException {
        String query = "SELECT * FROM ADDRESS ORDER BY ADDRESS_ID";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        ArrayList<Address> addressList = new ArrayList<>();
        while (results.next()) {
            Address address = new Address(results.getString("STREET_NAME"), results.getInt("STREET_NUMBER"),
                    results.getString("APPARTMENT"));
            addresses.add(address);
        }
        return addressList;
    }

    public void removeCustomer(int id) {
        boolean worked = false;

        try {
            String query = "DELETE FROM ADDRESS WHERE ADDRESS_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                worked = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (worked) {
            for (Address address : addresses) {
                if (address.getAddressID() == id) {
                    addresses.remove(address);
                }
            }
        }
    }
}
