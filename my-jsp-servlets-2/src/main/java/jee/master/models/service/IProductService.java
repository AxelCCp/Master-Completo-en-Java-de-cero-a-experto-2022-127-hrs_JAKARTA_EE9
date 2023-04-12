package jee.master.models.service;

import jee.master.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public List<Product> list();
    public Optional<Product> getProductById(Long id);
    public void saveProduct(Product product);
    public void deleteById(Long id);

}
