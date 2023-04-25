package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.ClienteDetalle;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class A9_HibernateAsociacionesOneToOne_detalleClienteEsDueñaDeLaRelacion {

    public static void main(String[] args) {
/*
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");
            em.persist(cliente);

            ClienteDetalle detalle = new ClienteDetalle();
            detalle.setCliente(cliente);
            em.persist(detalle);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

 */
    }
}
