package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class A4_HibernatePorId_2 {

    //465
    //EL METODO FIND() REUTILIZA LA INFORMACIÓN QUE QUEDA ALMACENADA EN EL CACHÉ, DESPUÉS DE UNA CONSULTA.

    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingresa un id: ");
        Long id = scan.nextLong();

        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);
        em.close();
    }
}
