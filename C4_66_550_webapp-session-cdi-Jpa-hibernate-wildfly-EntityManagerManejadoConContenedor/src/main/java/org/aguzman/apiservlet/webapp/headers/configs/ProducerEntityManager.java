package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;

//550
//1 - EN LAS VERSIONES ANTERIORES DE ESTE PROYECTO, ESTA CON @ApplicationScoped Y SE CAMBIA A @RequestScoped PQ LE VAMOS A DECIR AL CONTENEDOR DE JPA, ..
//..EL CONTENEDOR DE PERSISTENCIA, QUE A SU VEZ ES MANEJADO POR EL CONTENEDOR DE WILDFLY, QUE NOS INYECTE UN ENTITY MANAGER,..
//..PERO LE DECIMOS QUE LO INYECTE DENTRO DEL REQUEST.

//2 - SE CAMBIA EL @PersistenceUnit POR EL @PersistenceContext : PQ ESTE MÉTODO YA NO VA A INYECTAR UN EntityManagerFactory, SINO QUE VA A INYECTAR UN EntityManager DIRECTAMENTE.
//.. A SU VEZ, EL EntityManager SE GUARDA EN EL PRODUCER QUE ES DEL TIPO DEL REQUEST. EN EL CONTEXTO DEL REQUEST. POR LO TANTO EL CICLO DE VIDA DE ESTE EntityManager ES EL REQUEST.

//3 - AQUI SE DEVUELVE EL ENTITY MANAGER, TAMBN DEL REQUEST, PARA Q SE PUEDA INYECTAR DIRECTAMENTE.

//-----------

//VENTAJAS :
// EL ENTITY MANAGER ES MANEJADO 100% POR EL CONTENEDOR. NO HAY Q PREOCUPARSE POR CREARLO O CERRAR LA CONEXIÓN.

//DESVENTAJA:
//LA CONEXIÓN SE CIERRA AUTOMATICAMENTE DESPUÉS DEL COMMIT.

@RequestScoped //@ApplicationScoped //1
public class ProducerEntityManager {

    @PersistenceContext(name = "ejemploJpa")        //@PersistenceUnit(name = "ejemploJpa") //2
    private EntityManager em;               //private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return em;          //3
    }
}
