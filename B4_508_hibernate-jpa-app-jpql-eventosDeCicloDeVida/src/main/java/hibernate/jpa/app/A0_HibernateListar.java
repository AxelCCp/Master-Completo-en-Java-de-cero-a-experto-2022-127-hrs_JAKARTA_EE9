package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class A0_HibernateListar {

    public static void main(String[]args){
        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente>clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(c -> System.out.println(c));
        em.close();
    }
}
