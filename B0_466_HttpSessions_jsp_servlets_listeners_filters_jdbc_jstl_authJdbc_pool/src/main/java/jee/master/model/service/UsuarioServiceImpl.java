package jee.master.model.service;

import jee.master.model.entity.Usuario;
import jee.master.model.repository.IUsuarioRepository;
import jee.master.model.repository.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements IUsuarioService{

    public UsuarioServiceImpl(Connection connection){
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.porUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public List<Usuario> userList() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(),e.getCause());
        }
    }


    private IUsuarioRepository usuarioRepository;
}
