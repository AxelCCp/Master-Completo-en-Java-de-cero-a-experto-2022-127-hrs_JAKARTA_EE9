package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class A7_HibernateEliminar {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingresa el id del cliente a eliminar: ");
        Long id = scan.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
