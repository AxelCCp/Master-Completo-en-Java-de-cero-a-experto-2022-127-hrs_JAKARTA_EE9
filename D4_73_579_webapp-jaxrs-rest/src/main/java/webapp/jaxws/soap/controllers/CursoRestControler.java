package webapp.jaxws.soap.controllers;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import webapp.jaxws.soap.models.Curso;
import webapp.jaxws.soap.models.services.CursoService;

import java.util.List;
import java.util.Optional;

//579
//1 - INDICA Q ES UN CONTROLADOR REST MAPEADO A RUTAS.
//2 - ESTE PRODUCES ES DIFERENTE AL PRODUCES Q SE USA PARA CREAR UN COMPENENTE EN EL CONTEXTO CDI. ESTE PRODUCES SE USA PARA DEFINIR EL TIPO DE RESPUESTA DE LO METODOS. SE PUEDE PONER A NIVEL DE CLASE O POR CADA MÉTODO.
    //2.1 - PARA APPLICATION_XML : EN EL ENTITY HAY Q PONER LA ANOTACION @XmlRootElement PARA QUE GENERE CORRECTAMENTE EL XML.
    //2.2 - CON JSON, NO ES NECESARIO AGREGAR NINGUNA ANOTACIÓN EN LA CLASE ENTITY.

//580
//3 - @Consumes : INDICA EL FORMATO EN EL QUE VIENE EL OBJ QUE SE RECIBE POR PARAMETRO. EL OBJ CURSO. DEFINE EL FORMATO DEL OBJETO QUE SE VA A CONSUMIR.

@RequestScoped
@Path("/cursos")     //1
@Produces(MediaType.APPLICATION_XML)        //2
public class CursoRestControler {

    @Inject
    private CursoService service;

    @GET
    public List<Curso> listar(){
        return service.listar();
    }

    /*
    @GET
    public Response listar(){
        return Response.ok(service.listar()).build();
    }
    */

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id){
        Optional<Curso>optionalCurso = service.porId(id);
        if(optionalCurso.isPresent()){
            //return Response.ok(optionalCurso.get(),MediaType.APPLICATION_XML).build();
            return Response.ok(optionalCurso.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)        //3
    public Response crear(Curso curso){
        try{
            Curso nuevoCurso = service.guardar(curso);
            return Response.ok(nuevoCurso).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response editar(@PathParam("id") Long id, Curso curso){
        Optional<Curso>cursoOptional = service.porId(id);
        if(cursoOptional.isPresent()) {
            Curso nuevoCurso = cursoOptional.get();
            nuevoCurso.setNombre(curso.getNombre());
            nuevoCurso.setDescripcion(curso.getDescripcion());
            nuevoCurso.setDuracion(curso.getDuracion());
            nuevoCurso.setInstructor(curso.getInstructor());
            try{
                service.guardar(nuevoCurso);
                return Response.ok(nuevoCurso).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") Long id){
        Optional<Curso>cursoOptional = service.porId(id);
        if(cursoOptional.isPresent()){
            try{
            service.eliminar(cursoOptional.get().getId());
            return Response.noContent().build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
