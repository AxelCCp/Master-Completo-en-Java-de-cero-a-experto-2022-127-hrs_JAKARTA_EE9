package hibernate.jpa.app;

import hibernate.jpa.app.entity.Alumno;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class C3_HibernateFetchManyToMany {

    public static void main(String[] args) {


        //FETCH : RECORDAR Q FETCH ES PARA POBLAR LOS DATOS EN UN OBJ. EJ : POBLAR LOS CURSOS EN EL OBJ ALUMNO.
        //DISTINCT : SE PONE PARA Q NO REPITA AL ALUMNO , EL N NUMERO DE CURSOS EN EL QUE ESTÉ REGISTRADO.

        EntityManager em = JpaUtil.getEntityManager();

        //List<Alumno> alumnos = em.createQuery("select a from Alumno a", Alumno.class).getResultList();              //TODOS LOS ALUMNOS

        //List<Alumno> alumnos = em.createQuery("select distinct a from Alumno a left outer join fetch a.cursos", Alumno.class).getResultList();              //TRAE A LOS ALUMNOS, TENGAN O NO TENGAN CURSOS.

        List<Alumno> alumnos = em.createQuery("select distinct a from Alumno a inner join fetch a.cursos", Alumno.class).getResultList();      //TRAE SOLO A LOS ALUMNOS CON CURSO


        alumnos.forEach(a -> System.out.println(a.getNombre() + ", cursos: " +  a.getCursos()));

        em.close();

    }

}
