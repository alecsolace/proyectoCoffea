package com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanvalero.coffea.domain.*;

public class CartLineDAO {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<CartLine> cartLines;

    private Connection connection;

    public CartLineDAO() throws SQLException {
        connect();
        // cartLines = getAllCarts();
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

    public ArrayList<CartLine> getAllCartLines() {
        ArrayList<CartLine> cartLineList = new ArrayList<>();

        try {

            String query = "SELECT * FROM CART_LINE ORDER BY CART_LINE_ID";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            ProductDAO productDAO = new ProductDAO();
            ArrayList<Product> productList = productDAO.get_products();

            CartDAO cartDAO = new CartDAO();
            ArrayList<Cart> cartList = cartDAO.getCarts();

            while (results.next()) {
                for (Product product : productList) {
                    if (product.getProductID() == results.getInt("PRODUCT_ID")) {
                        for (Cart cart : cartList) {
                            if (cart.getCartID() == results.getInt("CART_ID")) {

                                CartLine cartLine = new CartLine(results.getInt("CART_LINE_ID"), product, cart,
                                        results.getInt("CANTIDAD"), results.getDouble("PRICE"));
                                cartLineList.add(cartLine);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cartLineList;
    }

    public int addCartLine(CartLine cartLine) {
        int rows = 0;

        try {
            String sql = "INSERT INTO CART_LINE (CART_LINE_ID, PRODUCT_ID, CART_ID, CANTIDAD, PRICE) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement sentencia = connection.prepareStatement(sql);
            sentencia.setInt(1, cartLine.getCartLineID());
            sentencia.setInt(2, cartLine.getProduct().getProductID());
            sentencia.setInt(3, cartLine.getCart().getCartID());
            sentencia.setInt(4, cartLine.getQuantity());
            sentencia.setDouble(5, cartLine.getPrice());

            rows = sentencia.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rows;
    }

    public ArrayList<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(ArrayList<CartLine> cartLines) {
        this.cartLines = cartLines;
    }
}