package jee.master.model.service;

import jee.master.model.entity.Producto;
import jee.master.model.repository.ProductoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//445
//1 y 2 : SE CREO UNA CLASE ServiceJdbcException, PARA MANEJAR LAS EXCEPCIONES. ESTO ES PARA PODER HACER EL ROLLBACK EN EL CONEXION FILTER, CUANDO HAYA ERRORES.

public class ProductoServiceJdbc implements IProductoService{

    public ProductoServiceJdbc(Connection connection){
        this.productoRepository = new ProductoRepositoryImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return productoRepository.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());                 //1
        }
    }

    @Override
    public Optional<Producto> porid(Long id) {
        try {
            return Optional.ofNullable(productoRepository.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());                 //2
        }
    }

    private ProductoRepositoryImpl productoRepository;
}


