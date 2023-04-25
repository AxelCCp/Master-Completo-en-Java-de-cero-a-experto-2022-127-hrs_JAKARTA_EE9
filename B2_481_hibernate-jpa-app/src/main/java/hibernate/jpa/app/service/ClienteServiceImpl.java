package hibernate.jpa.app.service;

import hibernate.jpa.app.dao.ClienteRepositorio;
import hibernate.jpa.app.dao.ICrudRepository;
import hibernate.jpa.app.entity.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements IClienteService {

    public ClienteServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new ClienteRepositorio(em);
    }

    @Override
    public List<Cliente> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Cliente cliente) {
        try {
            em.getTransaction().begin();
            repository.guardar(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    private EntityManager em;
    private ICrudRepository<Cliente> repository;
}
