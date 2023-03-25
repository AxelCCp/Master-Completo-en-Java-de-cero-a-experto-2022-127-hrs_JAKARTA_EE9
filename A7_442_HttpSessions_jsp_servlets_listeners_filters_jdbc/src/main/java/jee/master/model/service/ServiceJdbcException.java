package jee.master.model.service;

//445
//ESTA CLASE FUNCIONA COMO PUENTE DE COMUNICACIÓN ENTRE LA CLASE CONEXION FILTER Y LAS CLASES SERVICE, CUANDO OCURRE UNA EXCEPTION AL ENVIAR UNA QUERY A LA BBDD.

public class ServiceJdbcException extends RuntimeException{

    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
