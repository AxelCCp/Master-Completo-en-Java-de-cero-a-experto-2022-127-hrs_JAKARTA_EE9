package webapp.jaxws.soap.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

// 579
//1 - ESTA ES PARA EL CONTROLADOR REST, CUANDO EMITE RESPUESTAS DEL TIPO XML, PARA Q GENERE CORRECTAMENTE EL XML.
//583
//2 - UN CURSO PUEDE TENER UN INSTRUCTOR, PERO UN INSTRUCTOR PUEDE TENER VARIOS CURSOS.
//3 - PARA NO INCLUIR ESTE CAMPO EN EL XML.
//4 - PARA NO INCLUIR ENTE CAMPO EN EL JSON.
//5 - ESTA ESTÁ MEJOR PARA IGNORAR UN CAMPO EN EL JSON.

@XmlRootElement  //1
@Entity
@Table(name="cursoss")// si , con doble ss
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    //@XmlTransient    //3
    //@JsonbTransient    //4
    //@JsonIgnore             //5
    @ManyToOne(fetch = FetchType.LAZY)  //2
    private Instructor instructor;

    private Double duracion;

    public Curso() {
    }

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
}
