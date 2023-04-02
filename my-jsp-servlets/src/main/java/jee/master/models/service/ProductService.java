package jee.master.models.service;

import jee.master.models.dao.IGenericRepository;
import jee.master.models.dao.ProductRepository;
import jee.master.models.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductService implements  IProductService{

    public ProductService(Connection connection){
        this.productIRepository = new ProductRepository(connection);
    }

    @Override
    public List<Product> list() {
        try{
            return productIRepository.list();
        }catch (SQLException e){
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        try{
            return Optional.ofNullable(productIRepository.getById(id));
        }catch(SQLException e){
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveProduct(Product product) {
        try{
            productIRepository.save(product);
        }catch (SQLException e){
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            productIRepository.deleteById(id);
        }catch (SQLException e){
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }
    }

    private IGenericRepository<Product> productIRepository;

}
