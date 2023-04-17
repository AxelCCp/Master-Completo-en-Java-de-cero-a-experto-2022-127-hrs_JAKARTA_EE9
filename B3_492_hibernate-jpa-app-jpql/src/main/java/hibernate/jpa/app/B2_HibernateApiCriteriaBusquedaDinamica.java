package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B2_HibernateApiCriteriaBusquedaDinamica {

    public static void main(String[]args){

        Scanner scan = new Scanner(System.in);

        System.out.println("Filtro para nombre: ");
        String nombre = scan.nextLine();

        System.out.println("Filtro para el apellido: ");
        String apellido = scan.nextLine();

        System.out.println("Filtro para forma de pago: ");
        String formaPago = scan.nextLine();

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente>query = cb.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        List<Predicate>condiciones = new ArrayList<>();

        if(nombre != null && !nombre.isEmpty()){
            condiciones.add(cb.equal(from.get("nombre"), nombre));
        }

        if(apellido != null && !apellido.isEmpty()){
            condiciones.add(cb.equal(from.get("apellido"), apellido));
        }

        if(formaPago != null && !formaPago.isEmpty()){
            condiciones.add(cb.equal(from.get("formaPago"), formaPago));
        }

        query.select(from).where(cb.and(condiciones.toArray(new Predicate[condiciones.size()])));

        List<Cliente>clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);

        em.close();




    }
}
