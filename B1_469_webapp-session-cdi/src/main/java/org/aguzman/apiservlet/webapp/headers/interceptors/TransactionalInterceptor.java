package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.aguzman.apiservlet.webapp.headers.configs.MySqlConnPrincipal;
import org.aguzman.apiservlet.webapp.headers.services.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

//479           MANEJO DE LAS TRANSACCIONES USANDO ITERTERCEPTORES  Y ANOTACIONES
@TransactionalJdbc
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MySqlConnPrincipal
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        if(conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }

        try {
            log.info("-----> Iniciando transaccion " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
            Object resultado = invocation.proceed();
            conn.commit();
            log.info("-----> Realizando commit y Finalizando transaccion " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
            return resultado;
        } catch(ServiceJdbcException e) {
            conn.rollback();
            throw e;
        }
    }
}
