package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.Factura;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class A6_HibernateAsociacionesOneToManyBidireccionalFind {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 1L);
            cliente.setFormaPago("paypal");
            Factura f1 = new Factura("Compras de supermercado", 5000L);
            Factura f2 = new Factura("Compras de tecnología", 7000L);
            cliente.addFactura(f1);
            cliente.addFactura(f2);
            //em.merge(cliente);                            //EL MERGE ES OPCIONAL, YA QUE AL IR A BUSCAR AL CLIENTE CON EL FIND, EL CLIENTE YA QUEDA EN EL CONTEXTO DE PERSISTENCIA. Y LUEGO CON EL COMMIT , AUTOMATICAMENTE HACE EL UPDATE.
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
