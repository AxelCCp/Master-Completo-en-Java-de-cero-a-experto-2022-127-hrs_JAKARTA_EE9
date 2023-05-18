package webapp.jaxws.soap.models.services;

import jakarta.ejb.Local;
import webapp.jaxws.soap.models.Curso;

import java.util.List;
import java.util.Optional;


//579
//1 - POR DEFECTO, SI NO SE PONE, ES UN EJB LOCAL.

@Local  //1
public interface CursoService {

    List<Curso> listar();
    Curso guardar(Curso curso);
    Optional<Curso> porId(Long id);
    void eliminar(Long id);
}
