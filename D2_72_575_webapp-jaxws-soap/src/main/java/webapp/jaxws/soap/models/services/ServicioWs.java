package webapp.jaxws.soap.models.services;

import jakarta.jws.WebService;
import webapp.jaxws.soap.models.Curso;

import java.util.List;

//575
//1 - QUIERE DECIR Q TODOS LOS MÉTODOS DE ESTA INTERFAZ, SE VAN A PUBLICAR EN EL SERVICIO WEB.

@WebService //1
public interface ServicioWs {

    String saludar(String persona);
    List<Curso> listar();
    Curso crear(Curso curso);

}
