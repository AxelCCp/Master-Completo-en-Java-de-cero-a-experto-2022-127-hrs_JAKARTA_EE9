package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.Factura;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class A6_HibernateAsociacionesOneToManyBidireccional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");
            Factura f1 = new Factura("Compras de supermercado", 5000L);
            Factura f2 = new Factura("Compras de tecnología", 7000L);
            cliente.getFacturas().add(f1);
            cliente.getFacturas().add(f2);
            f1.setCliente(cliente);
            f2.setCliente(cliente);
            em.persist(cliente);
            em.getTransaction().commit();
            System.out.println(cliente);
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

}
