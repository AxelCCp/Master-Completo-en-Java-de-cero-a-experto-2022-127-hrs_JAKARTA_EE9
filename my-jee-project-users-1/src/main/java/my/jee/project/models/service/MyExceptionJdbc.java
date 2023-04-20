package my.jee.project.models.service;

public class MyExceptionJdbc extends RuntimeException {

    public MyExceptionJdbc(String message, Throwable cause){
        super(message, cause);
    }

    public MyExceptionJdbc(String message){
        super(message);
    }
}
