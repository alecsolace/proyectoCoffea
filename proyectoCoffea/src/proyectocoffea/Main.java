package proyectocoffea;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author alecsolace
 */
public class Main {
    public static User[] userList;

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
            
           // User u1 = new User("Prueba", "Prueba", "jhjhj");
            String query = "SELECT * FROM CUSTOMERS";

            
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getUsers() throws SQLException {
        Connection connection;
        String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
        connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM CUSTOMERS";
        String getNumber = "SELECT COUNT(*) FROM CUSTOMERS";
        ResultSet res = stmt.executeQuery(getNumber);
        int numRes = 0;
        while (res.next()) {
            numRes = res.getInt(1);
        }
        userList = new User[numRes];
        ResultSet results = stmt.executeQuery(query);
        int cont = 0;
        while (results.next()) {
            User user = new User(results.getString("Name"), results.getString("Last_name"), results.getString("email"));
            userList[cont] = user;
        }

    }
}
