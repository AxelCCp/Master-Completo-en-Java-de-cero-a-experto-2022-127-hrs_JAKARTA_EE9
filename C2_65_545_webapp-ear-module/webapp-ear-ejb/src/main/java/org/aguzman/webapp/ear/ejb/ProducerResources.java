package org.aguzman.webapp.ear.ejb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

//546
//1 - ESTE SE INYECTA. EL SERVIDOR DE APLICACIONES NOS CREA UN FACTORY POR APLICACION, POR ESO SE USA @ApplicationScoped.
     //ESTO NOS GENERA UN ENTITY MANAGER POR CADA REQUEST. UNA CONEXION A LA BBDD POR CADA REQUEST.
//2 - METODO QUE GENERA EL BEAN DE EntityManager. @Produces ES PARA REGISTRARLO EN EL CONTENEDOR Y GENERAR EL COMPONEENTE. Y @RequestScoped PARA REGISTRARLO EN EL CONTEXTO.
//3 - SE CIERRA LA CONEXIÓN AL FINALIZAR EL REQUEST


@ApplicationScoped
public class ProducerResources {

    //1
    @PersistenceUnit(name = "ejemploJpa")
    private EntityManagerFactory emf;

    //2
    @Produces
    @RequestScoped
    private EntityManager beanEntityManager(){
        return emf.createEntityManager();
    }

    //3
    public  void close(@Disposes EntityManager entityManager){
        if(entityManager.isOpen()){
            entityManager.close();
            System.out.println("Cerrando la conexion del entity manager!");
        }
    }


}
