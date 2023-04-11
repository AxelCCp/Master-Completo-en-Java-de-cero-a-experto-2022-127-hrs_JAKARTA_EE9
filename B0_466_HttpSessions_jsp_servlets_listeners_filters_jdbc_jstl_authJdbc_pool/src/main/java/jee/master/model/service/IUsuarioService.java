package jee.master.model.service;

import jee.master.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario>login(String username, String password);

    List<Usuario> userList();

}
