package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class HibernatePorId {

    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);
        System.out.println("Ingresa un id: ");
        Long id = scan.nextLong();
        query.setParameter(1, id);
        Cliente c = (Cliente)query.getSingleResult();                                                                       //ESTO DEVULVE SOLO UN RESULTADO. SI HAY MÁS DATOS CON FORMAPAGO DEBITO, HAY Q USAR EL GETRESULTLIST()
        List<Cliente> list = query.getResultList();
        System.out.println(c);
        System.out.println(".....................................");
        System.out.println("Lista :" +  list);
        em.close();
    }
}
