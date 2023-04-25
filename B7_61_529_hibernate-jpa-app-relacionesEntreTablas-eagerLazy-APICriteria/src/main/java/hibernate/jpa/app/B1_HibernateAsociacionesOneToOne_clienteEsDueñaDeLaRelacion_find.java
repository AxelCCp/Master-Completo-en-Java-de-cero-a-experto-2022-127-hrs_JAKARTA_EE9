package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.ClienteDetalle;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class B1_HibernateAsociacionesOneToOne_clienteEsDueñaDeLaRelacion_find {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 2L);
            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);
            cliente.setDetalle(detalle);
            em.getTransaction().commit();
            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
