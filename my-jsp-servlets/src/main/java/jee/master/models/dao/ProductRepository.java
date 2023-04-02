package jee.master.models.dao;

import jee.master.models.entity.Division;
import jee.master.models.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IGenericRepository <Product> {

    public ProductRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Product> list() throws SQLException {
        List<Product> products = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select p.*, d.name as division from products as p inner join division as d on (p.division_id = d.id) order by p.id asc")){
            while(rs.next()){
                Product product = ProductRepository.getProduct(rs);
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product getById(Long id) throws SQLException {
        Product product = null;
        try(PreparedStatement pst = connection.prepareStatement("select p.*, d.name as division from products as p inner join division as d on (p.division_id = d.id) where p.id=?")){
            pst.setLong(1,id);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    product = getProduct(rs);
                }
            }
        }
        return product;
    }

    @Override
    public void save(Product product) throws SQLException {
        String sql;
        if(product.getId() != null && product.getId() > 0){
            sql = "update products set name=?, precio=?, sku=?, categoria_id=? where id=?";
        } else {
            sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }

    private static Product getProduct(ResultSet resultSet) throws  SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setSku(resultSet.getString("sku"));
        product.setName(resultSet.getString("name"));
        product.setComments(resultSet.getString("comments"));

        Division division = new Division();
        division.setId(resultSet.getLong("division_id"));
        division.setName(resultSet.getString("division"));
        product.setDivision(division);

        product.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        product.setPuchaseDate(resultSet.getDate("puchase_date").toLocalDate());

        return  product;
    }

    private Connection connection;
}
