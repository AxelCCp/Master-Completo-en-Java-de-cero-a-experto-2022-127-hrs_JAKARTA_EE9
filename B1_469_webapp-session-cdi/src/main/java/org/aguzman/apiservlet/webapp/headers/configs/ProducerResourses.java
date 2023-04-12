package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//470
//1 - SE INDICA QUE ES UN MÉTODO QUE PRODUCE UN OBJ EN EL CONTEXTO
//2 - SE LE DA UN CONTEXTO

public class ProducerResourses {

    @Produces   //1
    @RequestScoped    //2
    @Named("conn")
    private Connection beanConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }

}
