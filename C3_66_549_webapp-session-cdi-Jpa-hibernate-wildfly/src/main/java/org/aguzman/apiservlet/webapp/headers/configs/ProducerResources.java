package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

//470
//1 - SE INDICA QUE ES UN MÉTODO QUE PRODUCE UN OBJ EN EL CONTEXTO.
//2 - SE LE DA UN CONTEXTO.
//3 - InjectionPoint se usa para poder mandar el bean del log a las clases donde se vaya a usar el log. InjectionPoint devuelve la metadata de la clase donde se pueda inyectar el logger.
//4 - LO REGISTRAMOS EN EL CONTENEDOR DE CDI. SE DEJA POR DEFECTO, O SEA SIN CONTEXTO.
//5 - //METODO PARA CERRAR LA CONEXION. ESTE METODO ES LLAMADO DE MANERA AUTOMATICA CON @DISPOSES  CUANDO FINALIZA EL CONTEXTO DEL BEAN QUE SE PASA POR ARGUMENTO  (Connection connection). CON @MySqlConnPrincipal SE INDICA Q ESA CONEXION SE VA A CERRAR.

//532
//6 - METODO PARA EL ENTITY MANAGER. ESTO FUNCIONA EN CONJUNTO CON EL ARCHIVO BEANS.XML (bean-discovery-mode="annotated">) ---> DEBE ESTAR EN "annotated", DE ESTA MANERA VA A REGISTRAR "UN SOLO ENTITY MANAGER" QUE SE CONFIGURA AQUÍ CON LAS ANOTACIONES.
//7 -
//8 - SE INYECTA UN RECURSO DESDE TOMCAT Y SE COMENTAN LAS 3 LINEAS DE CODIGO EN beanConnection().


//549
//9 - @PersistenceUnit : ES PARA INYECTAR EL ENTITY MANAGER FACTORY.
//10 -
//11 - SE CAMBIA EL RESOURCE, YA NO SE USA EL DE TOMCAT, SINO Q EL DE WILDFLY.

@ApplicationScoped
public class ProducerResources {

    @PersistenceUnit(name = "ejemploJpa")  //9
    private EntityManagerFactory emf;


    @Produces   //1
    @RequestScoped    //2
    @MySqlConnPrincipal      //@Named("conn")
    private Connection beanConnection() throws NamingException, SQLException {
        //Context initContext = new InitialContext();
        //Context envContext = (Context) initContext.lookup("java:/comp/env");
        //DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");
        return ds.getConnection();
    }

   //3
    @Produces //4
    private Logger beanLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


    //5
    public void close(@Disposes @MySqlConnPrincipal Connection connection) throws SQLException {
        connection.close();
        log.info("Cerrando la conexión.");
    }


    //6
    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return emf.createEntityManager();  //10
    }

    //7
    public void close(@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            log.info("Cerrando la conexión del entity manager.");
        }
    }


    //@Resource(name="jdbc/mysqlDB")  //8
    @Resource(lookup = "java:/MySqlDS")  //11
    private DataSource ds;


    @Inject
    private Logger log;

}
