package org.aguzman.webapp.ear.repository;

import org.aguzman.webapp.ear.ejb.entity.Usuario;

import java.util.List;

public interface UsuarioRepository {

    List<Usuario> listar();

}
