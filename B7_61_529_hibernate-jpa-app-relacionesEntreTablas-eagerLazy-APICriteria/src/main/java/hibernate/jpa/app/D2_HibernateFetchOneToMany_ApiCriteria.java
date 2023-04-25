package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import java.util.List;

//530

public class D2_HibernateFetchOneToMany_ApiCriteria {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);

        //  OPTIMIZAMOS LAS CONSULTAS CON UNA SOLA CONSULTA
        cliente.fetch("direcciones", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);

        query.select(cliente).distinct(true);                                                                   //distinct : PARA EVITAR REPETIDOS. EJ. UN CLIENTE CON 2 DIRECCIONES.
        List<Cliente>clientes = em.createQuery(query).getResultList();
        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));
        clientes.forEach(c -> System.out.println(c.getNombre() + ", detalle: " + c.getDetalle()));
        em.close();

    }
}
