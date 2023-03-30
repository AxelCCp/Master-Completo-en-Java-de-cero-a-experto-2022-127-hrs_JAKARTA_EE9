package jee.master.model.service;

import jee.master.model.entity.Categoria;
import jee.master.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listar();

    Optional<Producto> porid(Long id);

    void guardar(Producto producto);
    void eliminar(Long id);
    List<Categoria>listarcategoria();
    Optional<Categoria>porIdCategoria(Long id);

}
