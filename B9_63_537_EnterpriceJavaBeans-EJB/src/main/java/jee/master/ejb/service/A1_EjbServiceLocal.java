package jee.master.ejb.service;

import jakarta.ejb.Local;
import jee.master.ejb.model.Producto;

import java.util.List;

//541
//1 - INDICA QUE ES UNA INTERFAZ LOCAL. QUIERE DECIR Q EL EJB SE VA A USAR DENTRO DEL SERVIDOR DE APLICACIONES.
    //DENTRO DEL SERVIDOR DE APLICACIONES, QUIERE DECIR Q PUEDE ESTAR DENTRO DE ESTE PROYECTO O EN UN EJB DE OTRO PROYECTP, Q TMBN ESTARIA DENTRO DEL SERVIDOR DE APLICACIONES.

//542 : SE AÑADEN MÁS MÉTODOS.



@Local  //1
public interface A1_EjbServiceLocal {

    public String saludar(String nombre);
    //542...
    public List<Producto> listar();
    public Producto crear(Producto producto);

}
