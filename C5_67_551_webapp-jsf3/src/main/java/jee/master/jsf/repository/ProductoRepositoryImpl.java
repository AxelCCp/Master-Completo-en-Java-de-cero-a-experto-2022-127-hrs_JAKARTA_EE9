package jee.master.jsf.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jee.master.jsf.entities.Producto;

import java.util.List;

//561 - SE HACE UNA MODIFICACIÓN EN ESTA CLASE. EN MÉTODOS LISTAR Y PORID. TRABAJANDO CON JSF, HASTA AHORA PARA PODER OBTENER AL PRODUCTO CON LA CATEGORIA, SE ESTAN HACIENDO DOS CONSULTAS, UNA Q TRAE LOS PRODUCTOS Y OTRO Q TRAE LA CATEGORIA DEL PRODUCTO. PERO TRABAJANDO CON JSF Y CLASE CONVERTER, DA UN ERROR DE NO SESSION, YA Q SE CIERRA LA CONEXIÓN DESPUÉS DE LA PRIMERA CONSULTA A PRODUCTOS. POR LO TANTO HAY QUE JUNTAR LAS CONSULTAS, PARA Q NO DÉ ERRROR DE NO SESSION.

@RequestScoped
public class ProductoRepositoryImpl implements CrudRepository <Producto> {

    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() {
        //return em.createQuery("from Producto", Producto.class).getResultList();
        return em.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList();  //561
    }

    @Override
    public Producto porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Producto p left outer join fetch p.categoria where p.id=:id", Producto.class).setParameter("id",id).getSingleResult(); //561
    }

    @Override
    public void guardar(Producto producto) {
        if(producto.getId() != null && producto.getId() > 0) {
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = porId(id);
        em.remove(producto);
    }

}
