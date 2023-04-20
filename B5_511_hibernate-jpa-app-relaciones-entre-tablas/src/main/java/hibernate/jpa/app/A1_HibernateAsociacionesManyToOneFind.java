package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.Factura;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class A1_HibernateAsociacionesManyToOneFind {

    public static void  main(String[]args){

        EntityManager em = JpaUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 1L);

            Factura factura = new Factura("compras de oficina", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);
            System.out.println(factura);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }
}
