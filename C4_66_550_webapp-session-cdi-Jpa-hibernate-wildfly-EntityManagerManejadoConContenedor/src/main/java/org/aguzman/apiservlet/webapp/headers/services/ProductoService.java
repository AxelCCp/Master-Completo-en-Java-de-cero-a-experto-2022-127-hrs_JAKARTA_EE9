package org.aguzman.apiservlet.webapp.headers.services;

import jakarta.ejb.Local;
import org.aguzman.apiservlet.webapp.headers.models.entities.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.entities.Producto;

import java.util.List;
import java.util.Optional;

//549 - SE MODIFICAN LOS COMPONENETES PARA QUE SEAN EJB.
    //1 - @Local

@Local //1
public interface ProductoService {

    List<Producto> listar();

    Optional<Producto> porId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);

    List<Categoria> listarCategoria();

    Optional<Categoria> porIdCategoria(Long id);
}
