package cliente.ws.soap;

import webapp.jaxws.soap.models.services.Curso;
import webapp.jaxws.soap.models.services.ServicioWs;
import webapp.jaxws.soap.models.services.ServicioWsImplService;


//576
//1 - CON ESTO SE OBTIENE LA CLASE SERVICE PARA CONECTARNOS DE MANERA REMOTA CON EL WEB SERVICE.
//2 - INVOCA LOS MÉTODOS.


public class Main {
    public static void main(String[] args) {

        //1
        ServicioWs service = new ServicioWsImplService().getServicioWsImplPort();

        //2
        System.out.println("El saludo " + service.saludar("Axel"));

        Curso curso = new Curso();
        curso.setNombre("Angular");
        Curso respuesta =  service.crear(curso);
        System.out.println("Nuevo curso :" + curso.getNombre());
        service.listar().forEach(c -> System.out.println(c.getNombre()));
    }
}