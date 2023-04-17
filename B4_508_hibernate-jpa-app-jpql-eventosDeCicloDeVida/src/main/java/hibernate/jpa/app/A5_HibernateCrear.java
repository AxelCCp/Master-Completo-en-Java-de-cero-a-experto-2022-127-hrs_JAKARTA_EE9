package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class A5_HibernateCrear {

    public static void main(String[]args){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String nombre = JOptionPane.showInputDialog("Ingresa el nombre: ");
            String apellido = JOptionPane.showInputDialog("Ingresa el apellido: ");
            String pago = JOptionPane.showInputDialog("Ingresa la forma de pago: ");
            em.getTransaction().begin();
            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);
            em.persist(c);                                                  //ESTE ES EL GUARDAR.
            em.getTransaction().commit();

            System.out.println("El id del cliente registrado es: " + c.getId());
            c = em.find(Cliente.class, c.getId());
            System.out.println(c);
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
