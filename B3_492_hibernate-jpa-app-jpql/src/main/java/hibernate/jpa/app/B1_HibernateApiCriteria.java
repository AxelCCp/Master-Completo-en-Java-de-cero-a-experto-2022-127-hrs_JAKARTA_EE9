package hibernate.jpa.app;

//CLASE 499

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.Arrays;
import java.util.List;

public class B1_HibernateApiCriteria {

    public static void main(String[]args){

        System.out.println("::::::::::::::::::::::::::::::::::::::::::LISTAR::::::::::::::::::::::::::::::::::::::::::::::::");
        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cliente>query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);
        query.select(from);
        List<Cliente> clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println(":::::::::::::::::::::::::::::::::::::LISTAR WHERE EQUALS:::::::::::::::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.equal(from.get("nombre"), "Andres"));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println(":::::::::::::::::::::::::::::::::::::WHERE LIKE:::::::::::::::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.like(from.get("nombre"), "%and%"));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::WHERE LIKE LO MISMO PERO CON PARAMETRO::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String>nombreParamLike = criteria.parameter(String.class, "nombreParam");
        query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreParamLike)));
        clientes = em.createQuery(query).setParameter("nombreParam", "%jo%").getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::WHERE BETWEEN PARA RANGOS::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.between(from.get("id"), 2L, 6L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::WHERE IN::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(from.get("nombre").in(Arrays.asList("Andres", "John", "Lou")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::WHERE IN - LO MISMO PERO USANDO PARAMETRO::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<List>listParam = criteria.parameter(List.class, "nombres");
        query.select(from).where(from.get("nombre").in(listParam));
        clientes = em.createQuery(query).setParameter("nombres", Arrays.asList("Andres", "John", "Lou")).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::FILTRAR USANDO PREDICADOS MAYOR QUE O MAYOR IGUAL QUE::::::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.ge(from.get("id"), 3L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("....muestra solo los nombres que tengan caracteres mayor q 5.....");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::CONSULTA CON LOS PREDICADOS CONJUNCIÓN AND Y DISTUNCION OR::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre = criteria.equal(from.get("nombre"), "Andres");
        Predicate porFormaDePago = criteria.equal(from.get("formaPago"), "debito");
        query.select(from).where(criteria.and(porNombre, porFormaDePago));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::CONSULTAS CON ORDER BY ASC DESC::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::CONSULTAS POR ID::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<Long>idParam = criteria.parameter(Long.class, "id");
        query.select(from).where(criteria.equal(from.get("id"), idParam));
        Cliente cliente = em.createQuery(query).setParameter("id", 1L).getSingleResult();
        System.out.println(cliente);

        System.out.println("::::::::::::::::::::::::::CONSULTAR SOLO EL NOMBRE DE LOS CLIENTES::::::::::::::::::::::::::");
        CriteriaQuery<String>queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(from.get("nombre"));
        List<String>nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::CONSULTAR SOLO EL NOMBRE DE LOS CLIENTES - PERO Q NO SE REPITA EL NOMBRE JOHN Q ESTÁ 2 VECES::::::::::::::::::::::::::");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        //queryString.select(from.get("nombre")).distinct(true);
        queryString.select(criteria.upper(from.get("nombre"))).distinct(true);          //HACER APARECER LOS NOMBRES EN MAYUSCULA
        nombres = em.createQuery(queryString).getResultList();
        nombres.forEach(System.out::println);

        em.close();
    }
}
