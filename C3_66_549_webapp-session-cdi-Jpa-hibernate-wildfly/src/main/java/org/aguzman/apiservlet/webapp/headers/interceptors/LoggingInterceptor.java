package org.aguzman.apiservlet.webapp.headers.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;
//478
//1 - InvocationContext : SE PASA POR PARAMETRO EL CONTEXTO DE LA LLAMADA.
//2 - EJECUTA EL METODO QUE ESTAMOS ENVOLVIENDO Y DEVUELVE UN RESULTADO. Y LUEGO SE DEVUELVE UN RESULTADO.
//3 - ENVUELVE LA LLAMADA DE UN MÉTODO.

//LOS INTERCEPTORES SE REGISTRAN EN EL XML BEANS

@Logging
@Interceptor
public class LoggingInterceptor {

    @AroundInvoke           //3
    public Object logging(InvocationContext invocation) throws Exception {              //1
        log.info(" ***** Entrando antes de invocar al metodo " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
        Object resultado = invocation.proceed();        //2
        log.info(" ***** Saliendo de la invocación del metodo " + invocation.getMethod().getName() + " de la clase " + invocation.getMethod().getDeclaringClass());
        return resultado;
    }

    @Inject
    private Logger log;
}
