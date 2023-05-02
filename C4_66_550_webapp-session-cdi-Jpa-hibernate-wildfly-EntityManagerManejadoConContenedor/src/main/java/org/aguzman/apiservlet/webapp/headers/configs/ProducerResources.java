package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


//550

@ApplicationScoped
public class ProducerResources {

    @Produces
    @RequestScoped
    @MySqlConnPrincipal
    private Connection beanConnection() throws NamingException, SQLException {
        //Context initContext = new InitialContext();
        //Context envContext = (Context) initContext.lookup("java:/comp/env");
        //DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }


    @Produces
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    public void close(@Disposes @MySqlConnPrincipal Connection connection) throws SQLException {
        connection.close();
        log.info("Cerrando la conexión.");
    }

    //@Resource(name="jdbc/mysqlDB")
    @Resource(lookup = "java:/MySqlDS")
    private DataSource ds;

    @Inject
    private Logger log;

}
