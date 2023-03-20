package jee.master.model.service;

import jee.master.model.entity.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements IProductoService{

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(
                1L, "notebook", "computación", 12345),
                new Producto(2L, "escritorio", "oficina", 123456),
                new Producto(3L, "teclado", "oficina", 4563),
                new Producto(4L, "mouse", "computación",5345 ));
    }
}
