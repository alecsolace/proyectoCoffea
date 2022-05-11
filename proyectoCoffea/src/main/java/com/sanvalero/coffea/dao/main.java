/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.coffea.dao;

import com.sanvalero.coffea.domain.Category;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author A8-PC52
 */
public class main {
    public static void main(String[] args) throws SQLException {
        CategoryDAO categoryDAO = new CategoryDAO();
        ArrayList<Category> cat = categoryDAO.get_categories();
        System.out.println(cat.get(1));
    }
}
