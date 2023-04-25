package hibernate.jpa.app;

import hibernate.jpa.app.entity.Alumno;
import hibernate.jpa.app.entity.Curso;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class B6_HibernateAsociacionesManyToMany_desasignar_alumno_de_curso {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            //--- ESTE BLOQUE ES COPIADO DE LA B4

            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Cata", "Edu");
            Alumno alumno2 = new Alumno("Jano", "Fernan");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate y JPA", "Andres");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);

            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

            //--------522------- B6

            em.getTransaction().begin();
            Curso c2 = em.find(Curso.class, 3L);
            alumno1.getCursos().remove(c2);                                      //SE ELIIMINA AL ALUMNO DEL CURSO.
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
