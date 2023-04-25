package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.Direccion;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class A3_HibernateAsociacionesOneToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 2L);
            cliente.setFormaPago("mercado pago");

            Direccion d1 = new Direccion("El vergel", 123);
            Direccion d2 = new Direccion("Vasco de gama", 456);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            em.merge(cliente);
            System.out.println(cliente);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        }


    }
}
