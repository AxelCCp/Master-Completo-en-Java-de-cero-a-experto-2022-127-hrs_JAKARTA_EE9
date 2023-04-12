package jee.master.models.service;

public class ExceptionJdbcService extends  RuntimeException{

    public ExceptionJdbcService(String message){
        super(message);
    }

    public ExceptionJdbcService(String message, Throwable cause){
        super(message, cause);
    }

}
