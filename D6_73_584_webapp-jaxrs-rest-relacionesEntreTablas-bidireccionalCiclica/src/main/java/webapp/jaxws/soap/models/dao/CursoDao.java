package webapp.jaxws.soap.models.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import webapp.jaxws.soap.models.Curso;

import java.util.List;

//583
//1 - SE CARGA EN UNA SOLA CONSULTA EL CURSO CON EL INSTRUCTOR, YA Q SE ESTÁ USANDO FETCH TYPE LAZY. SI SE HACE UN "SELECT * FROM CURSO" DARÍA ERROR YA Q DESPUÉS DE LA PRIMERA CONSULTA PARA OBTENER EL CURSO, SE CIERRA LA CONEXIÓN Y LUEGO PARA OBTENER AL INSTRUCTOR YA NO HAY CONEXIÓN.

@RequestScoped
public class CursoDao implements CursoRepository{

    @Inject
    private EntityManager em;

    @Override
    public List<Curso> listar() {
        return em.createQuery("select c from Curso c left outer join fetch c.instructor", Curso.class).getResultList();     //1
    }

    @Override
    public Curso guardar(Curso curso) {
        if(curso.getId() != null && curso.getId() > 0){
            em.merge(curso);
        } else {
            em.persist(curso);
        }
        return curso;
    }

    @Override
    public Curso porId(Long id) {
        //return em.find(Curso.class, id);
        return em.createQuery("select c from Curso c left outer join fetch c.instructor where c.id=:id", Curso.class).setParameter("id", id).getSingleResult();     //1
    }

    @Override
    public void eliminar(Long id) {
        Curso c = porId(id);
        em.remove(c);
    }
}
