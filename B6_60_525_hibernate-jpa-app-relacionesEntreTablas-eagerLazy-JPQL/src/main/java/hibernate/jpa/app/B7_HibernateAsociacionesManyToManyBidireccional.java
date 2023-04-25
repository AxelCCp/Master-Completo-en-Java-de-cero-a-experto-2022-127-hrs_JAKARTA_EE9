package hibernate.jpa.app;

import hibernate.jpa.app.entity.Alumno;
import hibernate.jpa.app.entity.Curso;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class B7_HibernateAsociacionesManyToManyBidireccional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Cata", "Edu");
            Alumno alumno2 = new Alumno("Jano", "Fernan");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate y JPA", "Andres");

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso1);

            em.persist(alumno1);
            em.persist(alumno2);

            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

            em.getTransaction().begin();

            //SI EL ID QUE SE LE ASIGNA A C2 COINCIDE CON EL ID DEL CURSO DE LA LISTA, SE ELIMINA. ESTO FUNCIONA CON LOS EQUAL Q SE PUSIERON EN LAS CLASES.
            Curso c2 = new Curso("Curso Java", "Andres");
            c2.setId(3L);

            alumno1.removeCurso(c2);
            em.getTransaction().commit();

            System.out.println(alumno1);

        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

}
