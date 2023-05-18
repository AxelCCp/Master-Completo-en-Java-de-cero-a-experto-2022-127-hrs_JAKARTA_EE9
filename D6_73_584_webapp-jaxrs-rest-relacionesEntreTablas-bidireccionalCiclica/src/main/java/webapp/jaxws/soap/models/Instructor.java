package webapp.jaxws.soap.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//584
//1 - CURSO ES LA CLASE DUEÑA DE LA REALCION.
//2 - EAGER : SE PONE EAGER PARA NO QUEDARNOS SIN CONEXION. .... LUEGO SE QUITA , YA Q CON LA ANOTACION @JsonIgnoreProperties({"cursos"}) EN LA CLASE CURSO, YA NO YA A TRAER EN ESTA CLASE LOS CURSOS DEL INSTRUCTOR.
//3 - CascadeType.ALL : SI ELIMINO UN INSTRUCTOR, Q ESTE SE ELIMINE DEL CURSO.
//4 - "instructor" : TENIENDO ESTA ANOTACION EN LA CLASE CURSO, ES SUFICIENTE, PERO SE RECOMIENDA EN AMBOS LADOS.
//5 - "handler", "hibernateLazyInitializer" : COMO EL LAZY TRABAJA CON PROXY, A VECES SE ALMACENA BASURA EN EL CACHE Y ESTA BASURA LA TENEMOS QUE IGNORAR, PQ A VECES ESTO GEGERA UN BUG EN LAS VERSIONES DE JPA.

@Entity
@Table(name="instructores")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;

    @JsonIgnoreProperties({"instructor", "handler", "hibernateLazyInitializer"}) //4   //5
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL /*,fetch = FetchType.EAGER*/)        //1   //2   //3
    private List<Curso> cursos;

    public Instructor() {
        this.cursos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
