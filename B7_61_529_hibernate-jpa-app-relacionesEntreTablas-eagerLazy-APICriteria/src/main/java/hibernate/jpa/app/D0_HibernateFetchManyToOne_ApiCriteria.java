package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.Factura;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.List;

//529

public class D0_HibernateFetchManyToOne_ApiCriteria {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Factura>query = cb.createQuery(Factura.class);
        Root<Factura> facturaRoot = query.from(Factura.class);

        //CON UN JOIN NOS EVITAMOS REALIZAR UNA CONSULTA POR CADA FACTURA HACIA UN CLIENTE.
        //SE HACE UN LEFT JOIN HACIA LA RELACION CON CLIENTE.
        //SI NO SE PONE JoinType.LEFT, SE CONSIDERA COMO INNER JOIN.
        Fetch<Factura, Cliente> cliente = facturaRoot.fetch("cliente", JoinType.LEFT);
        //LUEGO SE HACE UN LEFT JOIN PARA TRAER EL DETALLE.
        cliente.fetch("detalle", JoinType.LEFT);

        query.select(facturaRoot);
        List<Factura> facturas = em.createQuery(query).getResultList();

        //EVITA USAR EL GETCLIENTE() SOLO, YA Q ESTE VA A INVOCAR AL TOSTRING() Q SE TRAE TODO, REALIZANDO MUCHAS CONSULTAS A LOS ATRIBUTOS CON RELACIONES ENTRE TABLAS..
        facturas.forEach(f -> System.out.println(f.getDescripcion() + " --> cliente: " + f.getCliente().getNombre()));
        em.close();
    }

}
