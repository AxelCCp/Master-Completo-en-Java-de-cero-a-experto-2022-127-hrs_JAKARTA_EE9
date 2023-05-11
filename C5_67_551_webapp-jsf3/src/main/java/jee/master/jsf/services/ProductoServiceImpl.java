package jee.master.jsf.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jee.master.jsf.entities.Categoria;
import jee.master.jsf.entities.Producto;
import jee.master.jsf.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//555

// EL REPOSITORY ES DEL CONTEXTO DEL REQUEST, MIENTRAS QUE LA CLASE ES STATELESS, QUE ES UN CONTEXTO MÁS AMPLIO QUE EL DEL REQUEST. HAY Q TENER EN CUENTA Q EL STATELESS ES COMPARTIDO ..
//.. POR VARIOS CLIENTES, PERO ESTO NO VA A GENERAR UN ERROR PQ HAY UN PROXY Q VA UNIENDO AL CLIENTE CON SU propia REQUEST, LO QUE GENERA UN REPOSITORY, CONEXION, ETC PROPIOS PARA CADA CLIENTE.

@Stateless
public class ProductoServiceImpl implements ProductoService {

    @Override
    public List<Producto> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));  //EXPLICACION 556, MIN 3.35
    }

    @Override
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.ofNullable(categoriaRepository.porId(id));
    }


    @Inject
    private CrudRepository<Producto> repository;
    @Inject
    private CrudRepository<Categoria> categoriaRepository;
}
