package org.aguzman.apiservlet.webapp.headers.util;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//532

public class JpaUtil {

    //SE CONFIGURA LA CONEXION. GENERA UNA INSTANCIA POR TODA LA APLICACION. ESTO ES COMO UN SINGLETON.
    //SE CREA UN ENTITY MANAGER, PERO ESTE SE CREA POR CADA REQUEST Y POR CADA CLIENTE QUE SE CONECTA.
    //POR LO TANTO CADA CLIENTE VA A TENER SU PROPIO ENTITY MANAGER Q VA A DURAR TOD0 EL CONTEXTO DEL REQUEST.
    //CON ESTO, CON UNA TRANSACCION VA A PODER HACER DIFERENTES OPERACIONES Y CONSULTAS DENTRO DEL CONTEXTO.

    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ejemploJpa");
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

}
