package proyectocoffea;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

/**
 *
 * @author alecsolace
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         * PARA NETBEANS
         * try {
         * Class.forName("oracle.jdbc.driver.OracleDriver");
         * } catch (ClassNotFoundException e) {
         * System.out.println("Oracle JDBC driver not found!");
         * e.printStackTrace();
         * return;
         * }
         * 
         * System.out.println("Oracle JDBC Driver Registered.");
         * 
         * Connection connection = null;
         * 
         * try {
         * connection =
         * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
         * "username", "password");
         * } catch (SQLException e) {
         * System.out.println("Connection Failed! Check username and password");
         * e.printStackTrace();
         * return;
         * }
         * 
         * if (connection != null) {
         * System.out.println("Connection to database successful");
         * } else {
         * System.out.println("Unable to connect to database.");
         * }
         */
        // SCRIPT INICIAL PARA RECOGER DATOS DDBB
        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");
            System.out.println("Conexión Establecida");

            ArrayList<Customer> customers = getCustomers();

            for (Customer customer : customers) {
                if (!customer.getEmail().equals("keevinaguirre@gmail.com")) {
                    Customer usuario1 = new Customer(0, "Alexander", "Aguirre", "keevinaguirre@gmail.com", "1234..");
                    System.out.println(usuario1.toString());
                } else {
                    System.out.println("Este usuario ya existe");
                }
            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Customer> getCustomers() throws SQLException {
        Connection connection;
        String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
        connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM CUSTOMERS";
        ArrayList<Customer> customerList = new ArrayList<>();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            // (int address, String name, String lastName, String email, String password)
            Customer customer = new Customer(results.getInt("ADDRESS_ID"), results.getString("NAME"),
                    results.getString("LAST_NAME"), results.getString("EMAIL"), results.getString("PASSWORD"));
            customerList.add(customer);
        }
        return customerList;
    }
}
