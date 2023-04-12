package jee.master.model.repository;

import jee.master.model.entity.Usuario;

import java.sql.SQLException;

public interface IUsuarioRepository extends IRepository<Usuario> {

    public Usuario porUsername(String username) throws SQLException;

}
