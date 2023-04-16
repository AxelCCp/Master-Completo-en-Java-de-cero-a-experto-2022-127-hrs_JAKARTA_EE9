package hibernate.jpa.app.dao;

import hibernate.jpa.app.entity.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteRepositorio implements ICrudRepository <Cliente> {

    public ClienteRepositorio(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Cliente> listar() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Override
    public Cliente porId(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public void guardar(Cliente cliente) {
        if(cliente.getId() != null && cliente.getId() > 0){
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = this.porId(id);
        em.remove(cliente);
    }

    private EntityManager em;
}
