package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class C1_Hibernate_fetchLazy_oneToMany_JoinFetch {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        //Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)                                         //ESTA CONSULTA GENERA MÁS DE UNA CONSULTA, PARA ONTENER LAS DIRECCIONES Y EL DETALLE.
        //Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones where c.id=:id", Cliente.class)       //CON ESTA TE TRAE TODOS LOS DATOS EN UNA SOLA CONSULTA. GENERA UN ENLACE ENTRE CLIENTE Y DIRECCIONES. EL LEFY JOIN TRAE A TODOS LOS CLIENTES Q TENGAN O NO DIRECCIONES. MINETRAS Q EL INER JOIN SOLO TRAE A LOS CLIENTES Q TENGAN DIRECIONNES. SE PONE FETCH PARA POBLAR LOS DATOS DE LAS DIRECCIONES EN EL OBJ CLIENTE.  EL OUTER PUEDE IR O NO IR.
        Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones left join fetch c.detalle where c.id=:id", Cliente.class)           //ESTA ES MÁS COMPLETA Q LA ANTERIOR YA Q TAMBN TRAE EL DETALLE.
                .setParameter("id",1L).getSingleResult();
        System.out.println("Nombre : " + cliente.getNombre() + ", direcciones : " +  cliente.getDirecciones());
        em.close();
    }

}
