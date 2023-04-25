package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class C2_HibernateFetchResultList {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        //List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();                                                     //ESTO DEVUELVE MÁS DE UNA CONSULTA.

        //List<Cliente> clientes = em.createQuery("select c from Cliente c left outer join fetch c.direcciones", Cliente.class).getResultList();                  //OPTIMIZAR PARA Q HAGA TODO EN UNA CONSULTA.

        //List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones", Cliente.class).getResultList();          //EN LA CONSULTA ANTERIOR, ANDRÉS, QUIEN TIENE 2 DIRECCIONES, APARECE 2 VECES Y AHORA SE MODIFICA LA CONSULTA PARA Q APAREZCA ANDRES UNA SOLA VEZ CON LAS 2 DIRECCIONES.

        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones left outer join fetch c.detalle", Cliente.class).getResultList();       //MODIFICANDO LA QUERY ANTERIOR, AHORA SE HACE UN JOIN PARA AGREGAR EL DETALLE CLIENTE.

        //ESTA NO SE PUEDE PQ NO SE PUEDE IR A BUSCAR MULTIPLES BOLSAS DE REGISTROS.
        // ESTO SE REFIERE A Q SE VA A BUSCAR INFORMACION DEL CLIENTE CON RELACION 1 CLIENTE A MUCHOS REGISTROS.
        //EN ESTE CASO ES MEJOR HACER 2 CONSULTAS SEPARADAS, UNA PARA LAS DIRECCIONES Y OTRA PARA LAS FACTURAS.
        //CON EL DETALLE ES DIFERENTE, YA Q ESTA ES UNA RELACION 1 A 1.
        //List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones left outer join fetch c.facturas left outer join fetch c.detalle", Cliente.class).getResultList();         //AHORA SE AGREGAN LAS FACTURAS.

        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()));

        em.close();

    }

}
