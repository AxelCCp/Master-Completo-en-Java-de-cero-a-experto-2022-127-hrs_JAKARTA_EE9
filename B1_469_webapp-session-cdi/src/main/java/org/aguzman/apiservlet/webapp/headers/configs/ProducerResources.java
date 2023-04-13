package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

//470
//1 - SE INDICA QUE ES UN MÉTODO QUE PRODUCE UN OBJ EN EL CONTEXTO
//2 - SE LE DA UN CONTEXTO

@ApplicationScoped
public class ProducerResources {

    @Produces   //1
    @RequestScoped    //2
    @MySqlConnPrincipal      //@Named("conn")
    private Connection beanConnection() throws NamingException, SQLException {
        //Context initContext = new InitialContext();
        //Context envContext = (Context) initContext.lookup("java:/comp/env");
        //DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }

    //InjectionPoint se usa para poder mandar el bean del log a las clases donde se vaya a usar el log. InjectionPoint devuelve la metadata de la clase donde se pueda inyectar el logger.
    @Produces //LO REGISTRAMOS EN EL CONTENEDOR DE CDI. SE DEJA POR DEFECTO, O SEA SIN CONTEXTO.
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    //METODO PARA CERRAR LA CONEXION. ESTE METODO ES LLAMADO DE MANERA AUTOMATICA CON @DISPOSES  CUANDO FINALIZA EL CONTEXTO DEL BEAN QUE SE PASA POR ARGUMENTO  (Connection connection). CON @MySqlConnPrincipal SE INDICA Q ESA CONEXION SE VA A CERRAR.
    public void close(@Disposes @MySqlConnPrincipal Connection connection) throws SQLException {
        connection.close();
        log.info("Cerrando la conexión.");
    }



    //SE INYECTA UN RECURSO DESDE TOMCAT Y SE COMENTAN LAS 3 LINEAS DE CODIGO.
    @Resource(name="jdbc/mysqlDB")
    private DataSource ds;

    @Inject
    private Logger log;

}
