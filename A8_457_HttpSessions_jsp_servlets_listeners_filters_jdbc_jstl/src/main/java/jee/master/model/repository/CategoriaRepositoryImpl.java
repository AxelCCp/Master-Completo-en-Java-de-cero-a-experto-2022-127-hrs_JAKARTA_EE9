package jee.master.model.repository;

import jee.master.model.entity.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryImpl implements IRepository<Categoria>{

    public CategoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria>categorias = new ArrayList<>();
        try(Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("select * from categorias")){
            while(rs.next()){
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }


    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement st = conn.prepareStatement("select * from categorias as c where c.id=?")){
            st.setLong(1, id);
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setId(rs.getLong("id"));
        return categoria;
    }

    private Connection conn;
}
