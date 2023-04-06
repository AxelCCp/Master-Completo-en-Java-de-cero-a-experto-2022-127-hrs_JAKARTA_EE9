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
            sql = "update products set sku=?, name=?, comments=?, division_id=?, release_date=?, puchase_date=?, price=? where id=?";
        } else {
            sql = "insert into products (sku, name, comments, division_id, release_date, puchase_date, price) values (?,?,?,?,?,?,?)";
        }
        try(PreparedStatement st = connection.prepareStatement(sql)){
            if(product.getId() != null && product.getId() > 0){
                st.setLong(8, product.getId());
            }
            st.setString(1, product.getSku());
            st.setString(2, product.getName());
            st.setString(3, product.getComments());
            st.setLong(4, product.getDivision().getId());
            st.setDate(5, Date.valueOf(product.getReleaseDate()));
            st.setDate(6, Date.valueOf(product.getPuchaseDate()));
            st.setDouble(7, product.getPrice());
            st.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "delete from products where id=?";
        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setLong(1, id);
            pst.executeUpdate();
        }
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
        product.setPrice(resultSet.getInt("price"));
        return  product;
    }

    private Connection connection;
}
