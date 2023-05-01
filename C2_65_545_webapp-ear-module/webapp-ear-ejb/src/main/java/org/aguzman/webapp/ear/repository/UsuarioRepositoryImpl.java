package org.aguzman.webapp.ear.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.aguzman.webapp.ear.ejb.entity.Usuario;

import java.util.List;

//1 - ESTE SE DEJA COMO UN COMPONENETE CDI. NO VA A SER UN EJB. --- @RequestScoped , SE PONE ESTA PQ SE LE VA A INJECTAR EL EntityManager. Y EL EntityManager TAMBN ES DEL CONTEXTO DEL REQUEST. ESTO TAMBN PODRÍA SER DEL TIPO @ApplicationScoped.

@RequestScoped    //1
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Usuario> listar() {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }


}
