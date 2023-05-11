package jee.master.jsf.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="categorias")
public class Categoria {

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

    //561 : EL MÉTODO EQUALS SE VA A USAR PARA POBLAR LA CATEGORIA EN EL DESPLEGABLE DE CATEGORIAS, EN EL FORMULARIO DE EDITAR PRODUCTO.PARA Q APAREZCA LA CATEGORIA Q YA TIENE SELECCIONADA. SE CONTRUYE SOLO CON EL CAMPO ID. NO SE INCLUYÓ EL HASHCODE.
    //VA A COMPARAR CADA CATEGORIA DE LA LISTA SELECT CON LA CATEGORIA DEL PRODUCTO.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}
