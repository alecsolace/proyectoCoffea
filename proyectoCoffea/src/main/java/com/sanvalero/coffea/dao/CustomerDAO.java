package com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.sanvalero.coffea.domain.Customer;

public class CustomerDAO {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Customer> customers;

    private Connection connection;

    public CustomerDAO() throws SQLException {
        connect();
        customers = getAllCustomers();
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

    /**
     * Desconecta de la base de datos
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * Adds a customer to the database
     *
     * @param customer
     * @param movie    The customer with the information you want to add
     * @return
     * @throws SQLException
     */
    public int addUser(Customer customer) throws SQLException {
        int status = 0;
        try {

            String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, NAME, LAST_NAME, EMAIL, PASSWORD) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement sentencia = connection.prepareStatement(sql);
            sentencia.setInt(1, customer.getUserID());
            sentencia.setString(2, customer.getName());
            sentencia.setString(3, customer.getLastName());
            sentencia.setString(4, customer.getEmail());
            sentencia.setString(5, customer.getPassword());
            status = sentencia.executeUpdate();
            return status;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    /**
     * Obtains an ArrayList with all the customers present in the DDBB
     *
     * @return An ArrayList of Customers
     * @throws java.sql.SQLException
     */
    public ArrayList<Customer> getAllCustomers() throws SQLException {

        String query = "SELECT * FROM CUSTOMERS ORDER BY CUSTOMER_ID";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        ArrayList<Customer> customerList = new ArrayList<>();
        while (results.next()) {

            Customer customer = new Customer(results.getString("NAME"),
                    results.getString("LAST_NAME"), results.getString("EMAIL"), results.getString("PASSWORD"));
            customer.setUserID(results.getInt("CUSTOMER_ID"));
            customerList.add(customer);

        }
        return customerList;
    }

    /**
     * Elimina una película
     *
     * @param id El id de la pelicula a eliminar
     */
    public void removeCustomer(int id) {

        boolean worked = false;

        try {
            String query = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
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
            for (Customer customer : customers) {
                if (customer.getUserID() == id) {
                    customers.remove(customer);
                }
            }
        }
    }

    /**
     * Modifica la información de una pelicula
     *
     * @param movie La película con la información a modificar
     */
    public void modifyCustomer(Customer customer) {
        // TODO
    }

}
