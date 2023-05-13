package webapp.jaxws.soap.models.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import webapp.jaxws.soap.models.Curso;
import webapp.jaxws.soap.models.dao.CursoDao;
import webapp.jaxws.soap.models.dao.CursoRepository;

import java.util.Arrays;
import java.util.List;


//575
//1 - QUIERE DECIR Q TODOS LOS MÉTODOS DE ESTA INTERFAZ, SE VAN A PUBLICAR EN EL SERVICIO WEB.
//2 - ES PARA PUBLICAR EXCLUSIVAMENTE LOS MÉTODOS DE LA INTERFAZ Y NO LOS DE LA CLASE.
//3 - @WebMethod : PARA Q LOS MÉTODOS SE PUEDAN PUBLICAR EN EL SERVICIO WEB.
//4 - CON JPA SE AGREGA UN STATELESS, YA Q ES UNA CLASE DE SERVICIO Q EJECUTA OPERACIONES Y CONSULTAS.


@Stateless //4
@WebService(endpointInterface = "webapp.jaxws.soap.models.services.CursoServicioWs")//1           //2
public class CursoServicioWsImpl implements CursoServicioWs {

    @Inject
    private CursoRepository cursoDao;

    @Override
    @WebMethod //3
    public List<Curso> listar() {
        return cursoDao.listar();
    }

    @Override
    @WebMethod
    public Curso guardar(Curso curso) {
        return cursoDao.guardar(curso);
    }
}
