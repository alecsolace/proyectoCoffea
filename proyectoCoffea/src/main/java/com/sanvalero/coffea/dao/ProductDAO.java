/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.coffea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanvalero.coffea.domain.Category;
import com.sanvalero.coffea.domain.Product;

/**
 *
 * @author alecsolace
 */
public class ProductDAO {

    private static final String DRIVER = "com.oracle.database.jdbc";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Product> products;

    private Connection connection;

    public ProductDAO() throws SQLException {
        connect();
        products = getProducts();
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

    public ArrayList<Product> getProducts() throws SQLException {
        String query = "SELECT * FROM CUSTOMERS ORDER BY CUSTOMER_ID";
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery(query);
        ArrayList<Product> productList = new ArrayList<>();

        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categories = categoryDAO.getCategories();

        while (results.next()) {
            int category_ID = results.getInt("CATEGORY_ID");
            for (Category category : categories) {
                if (category.getCategoryID() == category_ID) {
                    Product product = new Product(category, results.getString("NAME"),
                            results.getString("DESCRIPTION"), results.getDouble("PRICE"), results.getInt("STOCK"));
                    productList.add(product);
                    break;
                }
            }
        }
        return productList;
    }

    public void addProduct(Product product) {

        try {

            String createUser = "INSERT INTO PRODUCTS (PRODUCT_ID, CATEGORY_ID, NAME, DESCRIPTION, PRICE, STOCK) VALUES(?,?,?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(createUser);

            userStatement.setInt(1, product.getProductID());
            userStatement.setInt(2, product.getCategory().getCategoryID());
            userStatement.setString(3, product.getName());
            userStatement.setString(4, product.getDescription());
            userStatement.setDouble(5, product.getPrice());
            userStatement.setInt(6, product.getStock());

            userStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeProduct(int id) {
        boolean worked = false;

        try {
            String query = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
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
            for (Product product : products) {
                if (product.getProductID() == id) {
                    products.remove(product);
                }
            }
        }
    }
}
