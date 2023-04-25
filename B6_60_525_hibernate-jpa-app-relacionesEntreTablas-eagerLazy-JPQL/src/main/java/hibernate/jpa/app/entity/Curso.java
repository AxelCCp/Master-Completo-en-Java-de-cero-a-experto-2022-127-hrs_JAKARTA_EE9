package hibernate.jpa.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//521

@Entity
@Table(name="cursos")
public class Curso {

    public Curso() {
        alumnos = new ArrayList<>();
    }

    public Curso(String titulo, String profesor) {
        this();
        this.titulo = titulo;
        this.profesor = profesor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }

    //523 - RELACION BIDIRECCIONAL -SE USAN PARA DESASIGNAR ALUMNOS DE CURSOS Y CURSOS DE ALUMNOS, EN LAS CLASES ALUMNO Y CURSO. ESTO ES PARA LA CLASE B7.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String profesor;

    @ManyToMany(mappedBy = "cursos")// 523 - SE CONFIGURA LA RELACION INVERSA BIDIRECCIONAL. LA CLASE ALUMNOS ES LA DUEÑA DE LA RELACION, POR LO TANTO ALUMNO ES LA CLASE Q MAPEA. POR ESTO SE PONE DE ESTE LADO EL MAPPED BY CON EL ATRIBUTO CURSO Q ESTA EN ALUMNO.
    private List<Alumno>alumnos;


}
