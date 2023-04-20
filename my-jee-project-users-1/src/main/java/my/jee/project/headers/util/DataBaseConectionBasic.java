package my.jee.project.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConectionBasic {

    private static String url = "jdbc:mysql://localhost:3306/db-java-master-2?serverTimezone=America/Mexico_City";
    private static String username = "root";
    private static String password = "sasa";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
