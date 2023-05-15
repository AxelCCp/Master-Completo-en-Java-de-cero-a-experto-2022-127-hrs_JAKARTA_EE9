package cliente.jaxrs.rest;


//581 : SE CONSUME EL API REST CON JAX RS Y LA IMPLEMENTACION REST EASY. WILDFLY, POR DEBAJO USA REST EASY PARA EJECUTAR LAS APLICACIONES REST.
//1 - RUTA BASE PARA TODOS LOS MÉTODOS DEL API REST.
//2 - PARA LEER EL ENTITY SE CREA UN TIPO GENERICO y SE LE PASA EL <List<Curso>>. SE LE PONEN LAS LLAVES PQ SE CREA LA INSTANCIA AQUÍ MISMO.

//582
//3 - entityHeader : ES UNA ENTIDAD. UN OBJ QUE SE VA A GUARDAR EN EL REQUEST. (ESTO NO TIENE NADA Q VER CON EL ENTITY Q SE USA EN JPA).   ..
    //..  ESTE ENTITY RECIBE UN GENERICO, ACEPTA EL TIPO DE CONTENIDO, JSON O XML, QUE SE VA A MANDAR EN EL REQUEST, EN TE CASO "CURSO".  ..
    //.. SE LE PONE ENTITYHEADER PQ VA GUARDADO EN EL CURPO DEL REQUEST, PERO VA ASOCIADO A LA CABECERA Q CONTIENE EL TIPO DE CONTENIDO.

import cliente.jaxrs.rest.models.Curso;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        WebTarget rootUri = client.target("http://localhost:8080/webapp-jaxrs-rest/api").path("/cursos-json"); //1

        /*
        System.out.println("==================== POR ID ====================");
        Curso curso = rootUri.path("/2").request(MediaType.APPLICATION_JSON).get(Curso.class);
        System.out.println(curso);
         */

        System.out.println("==================== POR ID ====================");
        Response response = rootUri.path("/2").request(MediaType.APPLICATION_JSON).get();
        Curso curso = response.readEntity(Curso.class);
        System.out.println(curso);
        System.out.println(response.getStatus()  + " / " + response.getMediaType()  + " / " + response.getLocation()  + " / " + response.getDate()  + " / " + response.getLength()  + " / " + response.getHeaders());

        System.out.println("==================== LISTAR ====================");
        List<Curso> cursos = rootUri.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<Curso>>(){});  //2
        cursos.forEach(System.out::println);

        System.out.println("==================== CREANDO ====================");
        Curso cursoNuevo = new Curso();
        cursoNuevo.setNombre("Spring cloud");
        cursoNuevo.setDescripcion("Spring cloud - microservicios");
        cursoNuevo.setDuracion(25D);
        cursoNuevo.setInstructor("Andrés Guzmán");
        Entity<Curso> entityHeader = Entity.entity(cursoNuevo, MediaType.APPLICATION_JSON);     //3
        curso = rootUri.request(MediaType.APPLICATION_JSON).post(entityHeader, Curso.class);    //CREA EL CURSO
        System.out.println(curso + "\n");

        cursos = rootUri.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<Curso>>(){});   //LISTA ACTUALIZADA
        cursos.forEach(System.out::println);

        System.out.println("==================== EDITANDO ====================");
        Curso cursoEditado = curso;
        cursoEditado.setNombre("Microservicios con SpringCloud Eureka");
        entityHeader = Entity.entity(cursoEditado, MediaType.APPLICATION_JSON);
        curso = rootUri.path("/" + curso.getId()).request(MediaType.APPLICATION_JSON).put(entityHeader, Curso.class);
        System.out.println(curso);

        cursos = rootUri.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<Curso>>(){});   //LISTA ACTUALIZADA
        cursos.forEach(System.out::println);

        System.out.println("==================== ELIMINANDO ====================");
        rootUri.path("/" + curso.getId()).request().delete();

        cursos = rootUri.request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<Curso>>(){});   //LISTA ACTUALIZADA
        cursos.forEach(System.out::println);
    }
}