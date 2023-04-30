package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.aguzman.apiservlet.webapp.headers.interceptors.Logging;
import org.aguzman.apiservlet.webapp.headers.interceptors.TransactionalJdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@TransactionalJdbc        // 534 - SE COMENTA ESTA YA QUE AHORA SE PUEDE ELEGIR CON ANOTACIONES EN EL SERVICE SI SE VA A USAR JDBC O JPA. POR LO TANTO ESTO HAY Q DESACOPLARLO.
@Logging
@ApplicationScoped
@Stereotype
@Named
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
