package com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanvalero.coffea.domain.Cart;
import com.sanvalero.coffea.domain.Customer;

public class CartDAO {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Cart> carts;

    private Connection connection;

    public CartDAO() throws SQLException {
        connect();
        carts = getAllCarts();
    }

    public void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_CONEXION, USUARIO, CONTRASENA);
            System.out.println("ok connection");
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

    public ArrayList<Cart> getAllCarts() throws SQLException {

        ArrayList<Cart> cartList = new ArrayList<>();
        String query = "SELECT * FROM CART ORDER BY CART_ID";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        CustomerDAO customerDAO = new CustomerDAO();
        ArrayList<Customer> customerList = customerDAO.getCustomers();
        while (results.next()) {
            for (Customer customer : customerList) {
                if (customer.getUserID() == results.getInt("CUSTOMER_ID")) {

                    Cart cart = new Cart((results.getInt("CART_ID")), customer, results.getDouble("PRICE"),
                            results.getDate("ORDER_DATE"));
                    cartList.add(cart);
                }
            }
        }

        return cartList;
    }

    public int addCart(Cart cart) {

        int rows = 0;
        try {
            String sql = "INSERT INTO CART (CART_ID, CUSTOMER_ID, PRICE, ORDER_DATE) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement sentencia = connection.prepareStatement(sql);
            sentencia.setInt(1, cart.getCartID());
            sentencia.setInt(2, cart.getCustomer().getUserID());
            sentencia.setDouble(3, cart.getPrice());
            sentencia.setDate(4, cart.getOrderDate());

            rows = sentencia.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts = carts;
    }
}
