package jee.master.ejb.model;

import java.io.Serializable;

public class Producto implements Serializable {

    public Producto() {
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    private String nombre;

    static final long serialVersionUID = 42538573753423489L;

}
