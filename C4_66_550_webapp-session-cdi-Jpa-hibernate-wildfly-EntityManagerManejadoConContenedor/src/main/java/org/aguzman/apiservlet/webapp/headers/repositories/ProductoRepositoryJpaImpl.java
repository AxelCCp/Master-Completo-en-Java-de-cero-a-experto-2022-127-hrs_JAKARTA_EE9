package org.aguzman.apiservlet.webapp.headers.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.aguzman.apiservlet.webapp.headers.configs.Repositorio;
import org.aguzman.apiservlet.webapp.headers.models.entities.Producto;

import java.util.List;

@RepositoryJpa
@Repositorio
public class ProductoRepositoryJpaImpl implements Repository<Producto> {


    @Override
    public List<Producto> listar() throws Exception {
        //return em.createQuery("select p from Producto p", Producto.class).getResultList();
        //return em.createQuery("from Producto", Producto.class).getResultList();                                                 //ABREVIACIÓN.
        return em.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList();                      //535 - SE OPTIMIZA LA CONSULTA YA QUE AL ENTRAR EN LA PAGINA DE LA LISTA DE PRODUCTOS, SE GENERA LA CONSULTA PARA LISTAR LOS PRODUCTOS Y LUEGO LA CONSULTA PARA OBTENER LAS CATEGORIAS. POR LO TANTO SE QUIERE HACER TOD0 EN UNA CONSULTA.

    }

    @Override
    public Producto porId(Long id) throws Exception {
        return em.find(Producto.class, id);
    }

    @Override
    public void guardar(Producto producto) throws Exception {
        if(producto.getId() != null && producto.getId() > 0){
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        em.remove(porId(id));
    }

    @Inject
    private EntityManager em;


}
