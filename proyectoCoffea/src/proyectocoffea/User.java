package proyectocoffea;

import java.sql.Connection;
import java.sql.DriverManager;

public class User {
    private static int userID = 0;
    private String name;
    private String lastName;
    private String email;

    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
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
        try {
            Connection connection;
            String cadenaConexion = "jdbc:oracle:thin:@localhost:1521/XE";
            connection = DriverManager.getConnection(cadenaConexion, "admin", "admin");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarUsuario() {
        // Insertar la query de modificación.
    }
}
