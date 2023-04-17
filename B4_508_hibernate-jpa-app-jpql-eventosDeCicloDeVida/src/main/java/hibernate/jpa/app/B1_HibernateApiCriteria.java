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


        System.out.println("--------------------------------------------------------------503------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("::::::::::::::::::::::::::CONSULTAS CON ORDER BY ASC & DESC::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).orderBy(criteria.asc(from.get("nombre")), criteria.desc(from.get("apellido")));
        clientes = em.createQuery(query).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::CONSULTA POR ID::::::::::::::::::::::::::");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<Long>idParam2 = criteria.parameter(Long.class, "id");
        query.select(from).where(criteria.equal(from.get("id"), idParam2));
        Cliente cliente2 = em.createQuery(query).setParameter("id",1L).getSingleResult();
        System.out.println(cliente2);

        System.out.println("::::::::::::::::::::::::::CONSULTA SOLO EL NOMBRE DE LOS CLIENTES::::::::::::::::::::::::::");
        CriteriaQuery<String>queryString2 = criteria.createQuery(String.class);
        from = queryString2.from(Cliente.class);
        queryString2.select(from.get("nombre"));
        List<String>nombres2 = em.createQuery(queryString2).getResultList();
        nombres2.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::CONSULTA SOLO EL NOMBRE DE LOS CLIENTES - SIN REPETIR NOMBRES::::::::::::::::::::::::::");
        queryString2 = criteria.createQuery(String.class);
        from = queryString2.from(Cliente.class);
        //queryString2.select(from.get("nombre")).distinct(true);
        queryString2.select(criteria.upper(from.get("nombre"))).distinct(true);                     //MAYUSCULAS
        nombres2 = em.createQuery(queryString2).getResultList();
        nombres2.forEach(System.out::println);


        System.out.println("--------------------------------------------------------------504------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("::::::::::::::::::::::::::CONSULTA POR NOMBRES Y APELLIDOS CONCATENADOS::::::::::::::::::::::::::");
        queryString2 = criteria.createQuery(String.class);
        from = queryString2.from(Cliente.class);
        //queryString2.select(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido")));                       // NORMAL
        queryString2.select(criteria.upper(criteria.concat(criteria.concat(from.get("nombre"), " "), from.get("apellido"))));       //MAYUSCULAS
        nombres2 = em.createQuery(queryString2).getResultList();
        nombres2.forEach(System.out::println);

        System.out.println("--------------------------------------------------------------505------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("::::::::::::::::::::::::::CONSULTA DE CAMPOS PERSONALIZADOS DEL ENTITY CLIENTE CON WHERE::::::::::::::::::::::::::");
        CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));
        List<Object[]>registros = em.createQuery(queryObject).getResultList();
        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " + apellido);
        });


        System.out.println("::::::::::::::::::::::::::CONSULTA DE CAMPOS PERSONALIZADOS DEL ENTITY CLIENTE CON WHERE::::::::::::::::::::::::::");
        CriteriaQuery<Object[]> queryObject2 = criteria.createQuery(Object[].class);
        from = queryObject2.from(Cliente.class);
        queryObject2.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(criteria.like(from.get("nombre"), "%lu%"));
        List<Object[]>registros2 = em.createQuery(queryObject2).getResultList();
        registros2.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];
            System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " + apellido);
        });

        System.out.println("::::::::::::::::::::::::::CONSULTA DE CAMPOS PERSONALIZADOS DEL ENTITY CLIENTE CON WHERE - POR ID::::::::::::::::::::::::::");
        CriteriaQuery<Object[]> queryObject3 = criteria.createQuery(Object[].class);
        from = queryObject3.from(Cliente.class);
        queryObject3.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(criteria.equal(from.get("id"), 2L));
        Object[] registro = em.createQuery(queryObject3).getSingleResult();
        Long id = (Long) registro[0];
        String nombre = (String) registro[1];
        String apellido = (String) registro[2];
        System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " + apellido);


        System.out.println("--------------------------------------------------------------506------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("::::::::::::::::::::::::::CONTAR REGISTROS DE UNA CONSULTA CON COUNT::::::::::::::::::::::::::");
        CriteriaQuery<Long>queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.count(from.get("id")));
        Long count = em.createQuery(queryLong).getSingleResult();
        System.out.println(count);


        System.out.println("::::::::::::::::::::::::::SUMAR DATOS DE ALGUN CAMPO DE LA TABLA::::::::::::::::::::::::::");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.sum(from.get("id")));
        Long sum = em.createQuery(queryLong).getSingleResult();
        System.out.println(sum);


        System.out.println("::::::::::::::::::::::::::CONSULTA EL MÁXIMO ID::::::::::::::::::::::::::");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.max(from.get("id")));
        Long max = em.createQuery(queryLong).getSingleResult();
        System.out.println(max);

        System.out.println("::::::::::::::::::::::::::CONSULTA EL MÍNIMO ID::::::::::::::::::::::::::");
        queryLong = criteria.createQuery(Long.class);
        from = queryLong.from(Cliente.class);
        queryLong.select(criteria.min(from.get("id")));
        Long min = em.createQuery(queryLong).getSingleResult();
        System.out.println(min);

        System.out.println("::::::::::::::::::::::::::EJEMPLO VARIOS RESULTADOS DE FUNCIONES DE AGERGACION EN UNA SOLA CONSULTA::::::::::::::::::::::::::");
        queryObject = criteria.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);
        queryObject.multiselect(criteria.count(from.get("id")), criteria.sum(from.get("id")), criteria.max(from.get("id")), criteria.min(from.get("id")));
        registro = em.createQuery(queryObject).getSingleResult();
        count = (Long)registro[0];
        sum = (Long)registro[1];
        max = (Long)registro[2];
        min = (Long)registro[3];

        System.out.println("count: " + count + ". sum: " + sum + ". max: " + max + ". min: " + min);


        em.close();
    }
}
