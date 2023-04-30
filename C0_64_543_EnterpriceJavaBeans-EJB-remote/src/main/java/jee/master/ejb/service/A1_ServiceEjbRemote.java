package jee.master.ejb.service;


import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import jee.master.ejb.model.Producto;

import java.util.ArrayList;
import java.util.List;


//@Stateless
@Stateful
public class A1_ServiceEjbRemote implements A0_EjbService {

    public String saludar(String nombre){
        System.out.println("imprimiendo dentro del ejb con instancia: " + this);
        contador++;
        System.out.println("valor del contador en método saludar " + contador);
        return "Hola que tal " + nombre;
    }

    @Override
    public List<Producto> listar() {
        List<Producto>productos = new ArrayList<>();
        productos.add(new Producto("Peras"));
        productos.add(new Producto("Manzanas"));
        productos.add(new Producto("Naranjas"));
        return productos;
    }

    @Override
    public Producto crear(Producto producto) {
        Producto p = new Producto();
        p.setNombre(producto.getNombre());
        System.out.println("guardando producto :" + producto);
        return p;
    }


    private int contador;
}
