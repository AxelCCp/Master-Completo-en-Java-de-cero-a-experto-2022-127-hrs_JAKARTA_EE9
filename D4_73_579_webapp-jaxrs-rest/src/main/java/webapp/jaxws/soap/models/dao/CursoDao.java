package webapp.jaxws.soap.models.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import webapp.jaxws.soap.models.Curso;

import java.util.List;

@RequestScoped
public class CursoDao implements CursoRepository{

    @Inject
    private EntityManager em;

    @Override
    public List<Curso> listar() {
        return em.createQuery("from Curso", Curso.class).getResultList();
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
        return em.find(Curso.class, id);
    }

    @Override
    public void eliminar(Long id) {
        Curso c = porId(id);
        em.remove(c);
    }
}
