package jee.master.model.repository;

import jee.master.model.entity.Categoria;
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
            ResultSet rs = st.executeQuery("select p.*, c.nombre as categoria from productos as p inner join categorias as c on (p.categoria_id = c.id) order by p.id asc")){
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
        String sql;
        if(producto.getId() != null && producto.getId() > 0){
            sql = "update productos set nombre=?, precio=?, sku=?, categoria_id=? where id=?";
        }else{
            sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
        }
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, producto.getNombre());
            st.setInt(2, producto.getPrecio());
            st.setString(3, producto.getSku());
            st.setLong(4, producto.getCategoria().getId());

            if(producto.getId() != null && producto.getId() > 0){
                st.setLong(5, producto.getId());
            }else{
                st.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from productos where id=?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setLong(1, id);
            st.executeUpdate();
        }

    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        producto.setSku(rs.getString("sku"));
        producto.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        producto.setCategoria(c);
        return producto;
    }

    private Connection conn;
}
