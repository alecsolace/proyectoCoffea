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
import java.util.Random;

/**
 *
 * @author alecsolace
 */
public class ProductDAO {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Product> products;
    private ArrayList<Category> categories;
    private Connection connection;

    public ProductDAO() throws SQLException {
        connect();
        products = getProducts();
    }

    public ArrayList<Product> get_products() {
        return products;
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
        ArrayList<Product> productList = new ArrayList<>();
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM PRODUCTS ORDER BY PRODUCT_ID";
        ResultSet results = statement.executeQuery(query);

        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> categories = categoryDAO.get_categories();

        while (results.next()) {
            int category_ID = results.getInt("CATEGORY_ID");
            for (Category category : categories) {
                if (category.getCategoryID() == category_ID) {
                    Product product = new Product(category, results.getString("NAME"), results.getString("DESCRIPTION"),
                            results.getDouble("PRICE"), results.getInt("STOCK"), results.getString("IMAGE"));
                    product.setProductID(results.getInt("PRODUCT_ID"));
                    productList.add(product);
                }
            }
        }

        return productList;
    }

    public void addProduct(Product product) {

        try {

            String createUser = "INSERT INTO PRODUCTS (PRODUCT_ID, CATEGORY_ID, NAME, DESCRIPTION, PRICE, STOCK) VALUES(?,?,?,?,?,?,?)";
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

    public Product getBestSeller() throws SQLException {
        Product bestSeller = new Product(new Category("categoria"), "Placeholder", "Description", 31, 1,
                "../imagenes/");

        /*
         * CategoryDAO categoryDAO = new CategoryDAO();
         * categories = categoryDAO.getCategories();
         * if (categories.size() > 0 && products.size() > 0) {
         * for (Product product : products) {
         * if (product.getProductID() == 1) {
         * bestSeller = product;
         * }
         * }
         * }
         */
        for (Product product : products) {
            if (product.getProductID() == 2) {
                bestSeller = product;
            }
        }
        return bestSeller;
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

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

}
