package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import org.aguzman.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

//479 - MANEJO DE LAS TRANSACCIONES USANDO ITERTERCEPTORES  Y ANOTACIONES.
//534 - SE COPIO LA DE JDBC Y SE EDITÓ PARA JPA.

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        try {
            log.info("-----> Iniciando transaccion " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());

            em.getTransaction().begin();

            Object resultado = invocation.proceed();

            em.getTransaction().commit();

            log.info("-----> Realizando commit y Finalizando transaccion " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
            return resultado;
        } catch(ServiceJdbcException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
