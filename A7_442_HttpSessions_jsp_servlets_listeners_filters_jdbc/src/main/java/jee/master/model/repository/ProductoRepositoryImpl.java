package jee.master.model.repository;

import jee.master.model.entity.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//444 ... 1
//445 ... 2

public class ProductoRepositoryImpl implements IRepository<Producto>{

    public ProductoRepositoryImpl(Connection conn){
        this.conn = conn;
    }

    //1
    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto>productos = new ArrayList<>();
        try(Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select p.*, c.nombre as categoria from productos as p inner join categorias as c on (p.categoria_id = c.id)")){
            while(rs.next()){
                Producto producto = getProducto(rs);
                productos.add(producto);
            }
        }
        return productos;
    }

    //2
    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement st = conn.prepareStatement("select p.*, c.nombre as categoria from productos as p inner join categorias as c on (p.categoria_id = c.id) where p.id=?")){
            st.setLong(1,id);
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        producto.setTipo(rs.getString("categoria"));
        return producto;
    }

    private Connection conn;
}
