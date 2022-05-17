/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.coffea.domain;

import com.sanvalero.coffea.dao.ProductDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aaguirrem
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        Product bestSeller = productDAO.getBestSeller();
        ArrayList<Product> productList = productDAO.get_products();
        System.out.println("Funciona= " + productList.size());
    }

}
