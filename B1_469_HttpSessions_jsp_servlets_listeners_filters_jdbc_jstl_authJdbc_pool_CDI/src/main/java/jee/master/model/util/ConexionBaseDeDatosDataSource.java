package jee.master.model.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatosDataSource {

    public static Connection getConnection() throws SQLException {
        Context initContext = null;
        DataSource ds = null;
        try {
            initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/mysqlDB");
            Connection conn = ds.getConnection();
            return conn;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
