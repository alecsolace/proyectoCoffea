package proyectocoffea;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.sql.DriverManager;
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

        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/orcl";
            connection = DriverManager.getConnection(cadenaConexion, "admin", "admin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
