package hibernate.jpa.app;

import hibernate.jpa.app.dto.ClienteDto;
import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

//ESTUDIANDO HQL
public class B0_HibernateQueryLanguage {

    public static void main(String[]args){

        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("::::::::::::::::::::::::::::CONSULTAR TODOS::::::::::::::::::::::::::::");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("::::::::::::::::::::::::::::CONSULTAR POR ID::::::::::::::::::::::::::::");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class).setParameter("id",1L).getSingleResult();
        System.out.println(cliente);

        System.out.println("::::::::::::::::::CONSULTAR SOLO EL NOMBRE POR EL ID::::::::::::::::::::");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id=:id", String.class).setParameter("id",2L).getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("::::::::::::::::::CONSULTAS POR CAMPOS PERSONALIZADOS:::::::::::::::::::");
        Object[]objetoCliente = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class).setParameter("id", 1L).getSingleResult();
        Long id = (Long) objetoCliente[0];
        String nombre = (String) objetoCliente[1];
        String apellido = (String) objetoCliente[2];
        System.out.println(id + " " + nombre + " " + apellido);

        System.out.println(":::::::::::::::CONSULTAS POR CAMPOS PERSONALIZADOS LISTA::::::::::::::::");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class).getResultList();
        /*for(Object[] reg : registros){
            id = (Long) reg[0];
            nombre = (String) reg[1];
            apellido = (String) reg[2];
            System.out.println(id + " " + nombre + " " + apellido);
        }*/
        registros.forEach(reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println(idCli + " " + nombreCli + " " + apellidoCli);
        });

        System.out.println(":::::::::::::::CONSULTA POR CLIENTE Y FORMA DE PAGO::::::::::::::::");
        List<Object[]>registros1 = em.createQuery("select c, c.formaPago from Cliente c", Object[].class).getResultList();
        registros1.forEach(reg -> {
            Cliente c = (Cliente)reg[0];
            String formaPago = (String)reg[1];
            System.out.println("formaPago=" + formaPago + ", " + c);
        });

        System.out.println(":::::::::::::::CONSULTA QUE PUEBLA Y DEVUELVE OBJETO ENTITY DE UNA CLASE PERSONALIZADA::::::::::::::::");
        List<Cliente>cliente1 = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        //ESTO CONSULTA A TRAVES DE LA CLASE CLIENTE, PERO DEVUELVE LOS DATOS EN UN OBJ DTO. SE PONE EL PACKAGE EN LA CONSULTA, PARA QUE ENCUENTRE LA CLASE.
        System.out.println(":::::::::::::::CONSULTA QUE PUEBLA Y DEVUELVE OBJETO DTO DE UNA CLASE PERSONALIZADA::::::::::::::::");
        List<ClienteDto>clienteDto = em.createQuery("select new hibernate.jpa.app.dto.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class).getResultList();
        clienteDto.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA QUE QUE DEVUELVE SOLO LOS NOMBRES DE CLIENTE::::::::::::::::");
        List<String>nombres = em.createQuery("select c.nombre from Cliente c", String.class).getResultList();
        nombres.forEach(System.out::println);

        //ESTE ES EL CAMPO QUE NO QUEREMOS Q SE REPITA : distinct(c.nombre)
        System.out.println(":::::::::::::::CONSULTA QUE QUE DEVUELVE SOLO LOS NOMBRES DE CLIENTE - QUITA LOS NOMBRES REPETIDOS::::::::::::::::");
        List<String>nombres1 = em.createQuery("select distinct(c.nombre) from Cliente c", String.class).getResultList();
        nombres1.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA QUE QUE DEVUELVE SOLO FORMAS DE PAGO DE CLIENTE - QUITA LAS FORMAS DE PAGO REPETIDAS::::::::::::::::");
        List<String>formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class).getResultList();
        formasPago.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA CUANTAS FORMAS DE PAGO HAY::::::::::::::::");
        Long totalDeFormasDePago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class).getSingleResult();
        System.out.println(totalDeFormasDePago);

        System.out.println("--------------------------------------------------------------495------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------CONCATENAR ATRIBUTOS----------------------------------------------------------------------");

        System.out.println(":::::::::::::::CONSULTA CON NOMBRES Y APELLIDOS CONCATENADOS - FORMA 1::::::::::::::::");
        List<String>nombres2 = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c", String.class).getResultList();
        nombres2.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA CON NOMBRES Y APELLIDOS CONCATENADOS - FORMA 2::::::::::::::::");
        List<String>nombres3 = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class).getResultList();
        nombres3.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA CON NOMBRES Y APELLIDOS CONCATENADOS - EN MAYUSCULAS::::::::::::::::");
        List<String>nombres4 = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class).getResultList();
        nombres4.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA CON NOMBRES Y APELLIDOS CONCATENADOS - EN MINUSCULAS::::::::::::::::");
        List<String>nombres5 = em.createQuery("select lower(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class).getResultList();
        nombres5.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA PARA BUSCAR POR NOMBRE - 1 ::::::::::::::::");
        String param = "andres";
        List<Cliente> clientes2 = em.createQuery("select c from Cliente c where c.nombre like : parametro", Cliente.class).setParameter("parametro", param).getResultList();
        clientes2.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA PARA BUSCAR POR NOMBRE - 2 - ENCUENTRA SEGÚN UNA PARTE QUE SE LE PASA POR PARAMETRO::::::::::::::::");
        String param1 = "and";
        List<Cliente> clientes3 = em.createQuery("select c from Cliente c where c.nombre like : parametro", Cliente.class).setParameter("parametro", "%" + param + "%").getResultList();
        clientes3.forEach(System.out::println);

        //UNA BUENA PRACTICA ES CONVERTIR A MAYUSCULAS O MINUSCULAS AL BUSCAR POR NOMBRE, YA Q ALGUNOS MOTORES DE BBDD SON KEY SENTITIVE.

        System.out.println(":::::::::::::::CONSULTA PARA BUSCAR POR NOMBRE - 3 - ENCUENTRA SEGÚN UNA PARTE QUE SE LE PASA POR PARAMETRO::::::::::::::::");
        String param2 = "AND";
        List<Cliente> clientes4 = em.createQuery("select c from Cliente c where upper(c.nombre) like upper(: parametro)", Cliente.class).setParameter("parametro", "%" + param2 + "%").getResultList();
        clientes4.forEach(System.out::println);


        System.out.println("--------------------------------------------------------------496------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------BETWEEN AND ORDER BY ----------------------------------------------------------------------");

        System.out.println(":::::::::::::::CONSULTA POR RANGOS::::::::::::::::");
        List<Cliente>clientes5 = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class).getResultList();
        clientes5.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTA POR RANGOS DE CARACTERES::::::::::::::::");
        List<Cliente>clientes6 = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'P'", Cliente.class).getResultList();
        clientes6.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTAS CON ORDENAMIENTO::::::::::::::::");
        //PUEDES USAR asc O desc
        List<Cliente>clientes7 = em.createQuery("select c from Cliente c order by c.nombre asc", Cliente.class).getResultList();
        clientes7.forEach(System.out::println);

        System.out.println(":::::::::::::::CONSULTAS CON ORDENAMIENTO ENCADENADO::::::::::::::::");
        //PUEDES USAR asc O desc
        List<Cliente>clientes8 = em.createQuery("select c from Cliente c order by c.nombre asc, c.apellido asc", Cliente.class).getResultList();
        clientes8.forEach(System.out::println);


        System.out.println("--------------------------------------------------------------497------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------COUNT - SUM - MAX -MIN - AVG----------------------------------------------------------------------");


        System.out.println(":::::::::::::::CONSULTA TOTAL DE REGISTROS EN LA TABLA::::::::::::::::");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class).getSingleResult();
        System.out.println(total);

        System.out.println(":::::::::::::::CONSULTA - OBTENER EL VALOR MINIMO DE LOS ID'S::::::::::::::::");
        Long minId = em.createQuery("select min(c.id) as minimo from  Cliente c", Long.class).getSingleResult();
        System.out.println(minId);

        System.out.println(":::::::::::::::CONSULTA - OBTENER EL VALOR MAXIMO DE LOS ID'S::::::::::::::::");
        Long maxId = em.createQuery("select max(c.id) as maximo from  Cliente c", Long.class).getSingleResult();
        System.out.println(maxId);

        System.out.println(":::::::::::::::CONSULTA - CON NOMBRE Y SU LARGO::::::::::::::::");
        List<Object[]> datos = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class).getResultList();
        datos.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre = " + nom + ", largo = " + largo);
        });

        System.out.println(":::::::::::::::CONSULTA - CON EL NOMBRE MAS CORTO::::::::::::::::");
        Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(minLargoNombre);

        System.out.println(":::::::::::::::CONSULTA - CON EL NOMBRE MAS LARGO::::::::::::::::");
        Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
        System.out.println(maxLargoNombre);

        System.out.println(":::::::::::::::CONSULTAS - RESUMEN FUNCIONES AGREGACIONES COUNT MIN MAX AVG SUM::::::::::::::::");
        Object[]estadisicas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class).getSingleResult();
        Long min = (Long) estadisicas[0];
        Long max = (Long) estadisicas[1];
        Long sum = (Long) estadisicas[2];
        Long count = (Long) estadisicas[3];
        Double avg = (Double) estadisicas[4];
        System.out.println("min = " + min + ". max = " + max + ". sum = " + sum + ". count = " + count + ". avg = " + avg);


        System.out.println("--------------------------------------------------------------498------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------SUB QUERYS--------------------------------------------------------------------------");

        System.out.println(":::::::::::::::CONSULTAS - CON EL NOMBRE MÁS CORTO Y SU LARGO::::::::::::::::");
        List<Object[]> datos2 = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class).getResultList();
        datos2.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre = " + nom + ", largo = " + largo);
        });

        System.out.println(":::::::::::::::CONSULTAS - CON EL NOMBRE MÁS LARGO Y SU LARGO::::::::::::::::");
        List<Object[]> datos3 = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where length(c.nombre) = (select max(length(c.nombre)) from Cliente c)", Object[].class).getResultList();
        datos3.forEach(reg -> {
            String nom = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println("nombre = " + nom + ", largo = " + largo);
        });

        System.out.println(":::::::::::::::CONSULTA PARA OBTENER EL ÚLTIMO REGISTRO::::::::::::::::");
        Cliente ultimoCliente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class).getSingleResult();
        System.out.println(ultimoCliente);


        System.out.println(":::::::::::::::CONSULTA WHERE IN::::::::::::::::");
        List<Cliente> clientes9 = em.createQuery("select c from Cliente c where c.id in (1,2,9)", Cliente.class).getResultList();
        clientes9.forEach(System.out::println);


        System.out.println(":::::::::::::::CONSULTA WHERE IN - MISMA CONSULTA PERO PASANDO PARAMETROS::::::::::::::::");
        List<Cliente> clientes10 = em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class).setParameter("ids", Arrays.asList(1L,2L,9L, 40L)).getResultList();
        clientes10.forEach(System.out::println);


        em.close();

    }

}
