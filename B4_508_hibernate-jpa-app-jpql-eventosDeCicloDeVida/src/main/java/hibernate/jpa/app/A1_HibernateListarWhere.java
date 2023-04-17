package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class A1_HibernateListarWhere {

    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingresa una forma de pago: ");
        String pago = scan.next();
        query.setParameter(1, pago);
        //query.setMaxResults(1);                                                                                           //PUEDES MANEJAR LA RESPUESTA CON ESTO PARA UN MAXIMO DE RESULTADOS. ASÍ getSingleResult() NO DARÁ ERROR.
        Cliente c = (Cliente)query.getSingleResult();                                                                       //ESTO DEVULVE SOLO UN RESULTADO. SI HAY MÁS DATOS CON FORMAPAGO DEBITO, HAY Q USAR EL GETRESULTLIST()
        System.out.println(c);

        System.out.println(".....................................");
        List<Cliente> list = query.getResultList();

        System.out.println("Lista :" +  list);
        em.close();
    }
}
