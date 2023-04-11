package jee.master.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos {

    private static String url = "jdbc:mysql://localhost:3306/java-master-jakarta?serverTimezone=America/Mexico_City";
    private static String username = "root";
    private static String password = "sasa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
