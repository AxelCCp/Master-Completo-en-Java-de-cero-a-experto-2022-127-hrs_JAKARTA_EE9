package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class A6_HibernateEditar {
    public static void main(String[]xxx){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa el id: "));
            Cliente c = em.find(Cliente.class, id);
            String nombre = JOptionPane.showInputDialog("Ingresa el nombre: ", c.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingresa el apellido: ", c.getApellido());
            String pago = JOptionPane.showInputDialog("Ingresa la forma de pago: ", c.getFormaPago());
            em.getTransaction().begin();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);
            em.merge(c);
            em.getTransaction().commit();
            System.out.println(c);
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
