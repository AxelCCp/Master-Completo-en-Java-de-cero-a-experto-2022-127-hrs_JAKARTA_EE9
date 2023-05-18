package webapp.jaxws.soap.models.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import webapp.jaxws.soap.models.Curso;
import webapp.jaxws.soap.models.dao.CursoRepository;

import java.util.List;
import java.util.Optional;


//579
//1 - CON JPA SE AGREGA UN STATELESS, YA Q ES UNA CLASE DE SERVICIO Q EJECUTA OPERACIONES Y CONSULTAS.


@Stateless //1
public class CursoServiceImpl implements CursoService {

    @Inject
    private CursoRepository cursoDao;

    @Override
    public List<Curso> listar() {
        return cursoDao.listar();
    }

    @Override
    public Curso guardar(Curso curso) {
        return cursoDao.guardar(curso);
    }

    @Override
    public Optional<Curso> porId(Long id) {
        return Optional.ofNullable(cursoDao.porId(id));
    }

    @Override
    public void eliminar(Long id) {
        cursoDao.eliminar(id);
    }
}
