package proyectocoffea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class User {
    private static int userID = 0;
    private int address_ID;
    private String name;
    private String lastName;
    private String email;
    private String password;

    public User(int address, String name, String lastName, String email, String password) {
        this.address_ID = address;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        registrarUsuario();
        userID++;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void registrarUsuario() {
        // Insertar los metodos de la base de datos
        int filas = 0;
        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            connection = DriverManager.getConnection(cadenaConexion, "ADMIN", "ADMIN");

            String createUser = "INSERT INTO CUSTOMERS VALUES(?,?,?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(createUser);

            userStatement.setInt(1, userID);
            userStatement.setInt(2, this.address_ID);
            userStatement.setString(3, this.name);
            userStatement.setString(4, this.lastName);
            userStatement.setString(5, this.email);
            userStatement.setString(6, this.password);

            filas = userStatement.executeUpdate();
            System.out.println("Se han insertado: " + filas + " filas.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void modificarUsuario() {
        // Insertar la query de modificación.
    }
}
