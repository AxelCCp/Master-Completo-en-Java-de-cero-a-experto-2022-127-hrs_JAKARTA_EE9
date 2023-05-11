package jee.master.jsf.services;

import jakarta.ejb.Local;
import jee.master.jsf.entities.Categoria;
import jee.master.jsf.entities.Producto;

import java.util.List;
import java.util.Optional;

//555
//1 - ES IMPORTANTE Q SEA UN EJB PARA Q MANEJE TRANSACCIONES - EL BEGIN - COMMIT - ROLLBACK - ETC.

@Local     //1
public interface ProductoService {

    List<Producto>listar();
    Optional<Producto> porId(Long id);
    void guardar(Producto producto);
    void eliminar(Long id);
    List<Categoria> listarCategorias();
    Optional<Categoria> porIdCategoria(Long id);

}
