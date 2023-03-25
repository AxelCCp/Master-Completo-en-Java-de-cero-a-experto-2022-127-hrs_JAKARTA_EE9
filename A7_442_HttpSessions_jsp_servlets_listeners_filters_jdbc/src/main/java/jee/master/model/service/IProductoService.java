package jee.master.model.service;

import jee.master.model.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listar();

    Optional<Producto> porid(Long id);
}
