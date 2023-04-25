package hibernate.jpa.app;

import hibernate.jpa.app.entity.Alumno;
import hibernate.jpa.app.entity.Curso;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class B5_HibernateAsociacionesManyToMany_find_Bidireccional {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);

            Curso curso1 = em.find(Curso.class, 1L);
            Curso curso2 = em.find(Curso.class, 2L);

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso1);

            em.getTransaction().commit();

            System.out.println(alumno1);
            System.out.println(alumno2);

            em.getTransaction().begin();

            //SI EL ID QUE SE LE ASIGNA A C2 COINCIDE CON EL ID DEL CURSO DE LA LISTA, SE ELIMINA. ESTO FUNCIONA CON LOS EQUAL Q SE PUSIERON EN LAS CLASES.
            Curso c2 = new Curso("Curso de jakarta ee9", "Andres");
            c2.setId(2L);

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
