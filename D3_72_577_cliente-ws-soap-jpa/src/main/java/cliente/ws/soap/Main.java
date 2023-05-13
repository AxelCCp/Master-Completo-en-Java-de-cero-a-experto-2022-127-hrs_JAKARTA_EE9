package cliente.ws.soap;

import webapp.jaxws.soap.models.services.Curso;
import webapp.jaxws.soap.models.services.CursoServicioWs;
import webapp.jaxws.soap.models.services.CursoServicioWsImplService;

//577
//1 - CON ESTO SE OBTIENE LA CLASE SERVICE PARA CONECTARNOS DE MANERA REMOTA CON EL WEB SERVICE.
//2 - INVOCA LOS MÉTODOS.


public class Main {

    public static void main(String[] args) {

        //1
        CursoServicioWs service = new CursoServicioWsImplService().getCursoServicioWsImplPort();
        Curso curso = new Curso();
        curso.setNombre("React");
        curso.setDescripcion("react js");
        curso.setDuracion(50D);                 //ES UN DOUBLE
        curso.setInstructor("Andrés Guzmán");
        Curso respuesta =  service.guardar(curso);
        System.out.println("Nuevo curso : id:" + curso.getId() + " desc:" + curso.getNombre());
        service.listar().forEach(c -> System.out.println(c.getNombre()));
    }
}