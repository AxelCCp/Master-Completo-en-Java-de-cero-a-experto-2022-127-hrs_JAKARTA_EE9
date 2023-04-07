package jee.master.model.repository;

import jee.master.model.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements IUsuarioRepository{

    public UsuarioRepositoryImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario>listaUsuarios = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from usuarios")) {
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setEmail(rs.getString("email"));
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement st = connection.prepareStatement("select * from usuarios where username=?")){
            st.setString(1,username);
            try(ResultSet rs = st.executeQuery()){
                if(rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setEmail(rs.getString("email"));
                }
            }
        }
        return usuario;
    }

    private Connection connection;
}
