package org.aguzman.apiservlet.webapp.headers.repositories;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE})
public @interface RepositoryJpa {



}
