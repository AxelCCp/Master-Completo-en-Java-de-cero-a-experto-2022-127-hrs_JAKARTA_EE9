package webapp.jaxws.soap.models.dao;

import webapp.jaxws.soap.models.Curso;

import java.util.List;

public interface CursoRepository {

    List<Curso> listar();
    Curso guardar(Curso curso);
    Curso porId(Long id);
    void eliminar(Long id);

}
