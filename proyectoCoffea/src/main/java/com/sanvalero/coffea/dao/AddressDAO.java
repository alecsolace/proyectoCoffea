package com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanvalero.coffea.domain.Address;
import com.sanvalero.coffea.domain.Customer;

public class AddressDAO {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Address> addresses;

    private Connection connection;

    public AddressDAO() throws SQLException {
        connect();
        addresses = getAllAddresses();
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

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<Address> getAllAddresses() throws SQLException {
        String query = "SELECT * FROM ADDRESS ORDER BY ADDRESS_ID";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        ArrayList<Address> addressList = new ArrayList<>();
        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<Customer> customerList = customerDAO.getCustomers();

        while (results.next()) {
            for (Customer customer : customerList) {
                if (results.getInt("CUSTOMER_ID") == customer.getUserID()) {

                    Address address = new Address(customer, results.getString("STREET_NAME"),
                            results.getInt("STREET_NUMBER"),
                            results.getString("APPARTMENT"));
                    address.setAddressID(results.getInt("ADDRESS_ID"));
                    addressList.add(address);
                }
            }
        }
        return addressList;
    }

    public int addAddress(Address adress) {
        int rows = 0;
        try {
            String sql = "INSERT INTO ADDRESS (ADDRESS_ID, CUSTOMER_ID, STREET_NAME, STREET_NUMBER, APPARTMENT) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement sentencia = connection.prepareStatement(sql);
            sentencia.setInt(1, adress.getAddressID());
            sentencia.setInt(2, adress.getCustomer().getUserID());
            sentencia.setString(3, adress.getStreetName());
            sentencia.setInt(4, adress.getStreetNumber());
            sentencia.setString(5, adress.getAppartment());
            rows = sentencia.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public void removeAddress(int id) {
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
