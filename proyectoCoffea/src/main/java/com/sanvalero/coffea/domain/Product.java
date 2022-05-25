/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.coffea.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author alecsolace
 */
public class Product {

    private int productID = 0;
    private Category category;
    private String name, description, image;
    private double price;
    private int stock;

    public Product(Category category, String name, String description, double price, int stock, String image) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        productID++;
    }

    public void insertProduct() {
        int filas = 0;
        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");

            String createUser = "INSERT INTO PRODUCTS (PRODUCT_ID, CATEGORY_ID, NAME, DESCRIPTION, PRICE, STOCK) VALUES(?,?,?,?,?,?, ?)";
            PreparedStatement userStatement = connection.prepareStatement(createUser);

            userStatement.setInt(1, this.productID);
            userStatement.setInt(2, this.category.getCategoryID());
            userStatement.setString(3, this.name);
            userStatement.setString(4, this.description);
            userStatement.setDouble(5, this.price);
            userStatement.setInt(6, this.stock);
            userStatement.setString(7, this.image);

            filas = userStatement.executeUpdate();
            System.out.println("Se han insertado: " + filas + " filas.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
