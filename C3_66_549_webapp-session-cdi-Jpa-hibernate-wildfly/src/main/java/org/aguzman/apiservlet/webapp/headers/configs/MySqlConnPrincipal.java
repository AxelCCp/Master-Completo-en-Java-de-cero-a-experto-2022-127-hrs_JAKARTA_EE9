package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)                                      //ESTO QUIERE DECIR QUE SE VA A EJECUTAR EN TIEMPO DE EJECUCIÓN.
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR})                   //SE DEFINE DONDE SE VA  APLICAR ESTA ANOTACION.
public @interface MySqlConnPrincipal {


}
