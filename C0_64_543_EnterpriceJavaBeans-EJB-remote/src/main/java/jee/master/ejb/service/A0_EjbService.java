package jee.master.ejb.service;

import jakarta.ejb.Remote;
import jee.master.ejb.model.Producto;
import java.util.List;

@Remote
public interface A0_EjbService {

    public String saludar(String nombre);
    public List<Producto> listar();
    public Producto crear(Producto producto);

}
