
package main.java.com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.com.sanvalero.coffea.domain.Customer;

public class CustomerDAO {

    private static final String DRIVER = "com.oracle.database.jdbc";
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

    /**
     * Adds a customer to the database
     * 
     * @param movie The customer with the information you want to add
     * @throws SQLException
     */
    public void addUser(Customer customer) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS (CUSTOMER_ID, ADDRESS_ID, NAME, LAST_NAME, EMAIL, PASSWORD) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement sentencia = connection.prepareStatement(sql);
        sentencia.setInt(1, customer.getUserID());
        sentencia.setInt(2, customer.getAddress_ID());
        sentencia.setString(3, customer.getName());
        sentencia.setString(4, customer.getLastName());
        sentencia.setString(5, customer.getEmail());
        sentencia.setString(6, customer.getPassword());
        sentencia.executeUpdate();
    }

    /**
     * Obtains an ArrayList with all the customers present in the DDBB
     * 
     * @return An ArrayList of Customers
     */
    public ArrayList<Customer> getAllCustomers() throws SQLException {

        String query = "SELECT * FROM CUSTOMERS";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        ArrayList<Customer> customerList = new ArrayList<>();

        while (results.next()) {
            Customer customer = new Customer(results.getInt("ADDRESS_ID"), results.getString("NAME"),
                    results.getString("LAST_NAME"), results.getString("EMAIL"), results.getString("PASSWORD"));
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
        for (Customer customer : customers) {
            if (customer.getUserID() == id) {
                customers.remove(customer);
            }
        }
    }

    /**
     * Modifica la información de una pelicula
     * 
     * @param movie La película con la información a modificar
     */
    public void modifyCustomer(Customer customer) {

    }
}