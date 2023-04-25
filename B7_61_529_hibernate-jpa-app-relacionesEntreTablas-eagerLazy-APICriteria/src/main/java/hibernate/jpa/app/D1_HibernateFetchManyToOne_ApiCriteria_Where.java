package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.entity.Factura;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.List;

//529 - FILTRA POR CLIENTE ID

public class D1_HibernateFetchManyToOne_ApiCriteria_Where {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Factura>query = cb.createQuery(Factura.class);
        Root<Factura> facturaRoot = query.from(Factura.class);

        Join<Factura, Cliente> cliente = (Join)facturaRoot.fetch("cliente", JoinType.LEFT);

        cliente.fetch("detalle", JoinType.LEFT);

        query.select(facturaRoot).where(cb.equal(cliente.get("id"), 1L));
        List<Factura> facturas = em.createQuery(query).getResultList();

        //EVITA USAR EL GETCLIENTE() SOLO, YA Q ESTE VA A INVOCAR AL TOSTRING() Q SE TRAE TODO, REALIZANDO MUCHAS CONSULTAS A LOS ATRIBUTOS CON RELACIONES ENTRE TABLAS..
        facturas.forEach(f -> System.out.println(f.getDescripcion() + " --> cliente: " + f.getCliente().getNombre()));
        em.close();
    }

}
