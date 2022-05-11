package com.sanvalero.coffea.dao;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sanvalero.coffea.domain.Category;

public class CategoryDAO {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL_CONEXION = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USUARIO = "ADMIN";
    private static final String CONTRASENA = "ADMIN";
    private ArrayList<Category> categories = new ArrayList<Category>();

    private Connection connection;

    public CategoryDAO() throws SQLException {
        connect();
        categories = getCategories();
    }

    /**
     * Conecta con la base de datos
     */
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

    public ArrayList<Category> get_categories() {
        return categories;
    }
    
    
    
    public ArrayList<Category> getCategories() throws SQLException {
        
        ArrayList<Category> categoryList = new ArrayList<>();
            String query = "SELECT * FROM CATEGORIES ORDER BY CATEGORY_ID";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String name = results.getString("NAME");
                Category category = new Category(name);
                category.setCategoryID(results.getInt("CATEGORY_ID"));
                categoryList.add(category);
            }

        return categoryList;
    }

    public void removeCategory(int id) {
        boolean worked = false;

        try {
            String query = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";
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
            for (Category category : categories) {
                if (category.getCategoryID() == id) {
                    categories.remove(category);
                }
            }
        }
    }

}
