package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.entities.Usuario;


public interface UsuarioRepository extends Repository<Usuario>{
    Usuario porUsername(String username) throws Exception;
}
