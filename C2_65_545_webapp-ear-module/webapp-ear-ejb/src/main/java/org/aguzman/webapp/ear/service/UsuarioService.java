package org.aguzman.webapp.ear.service;

import jakarta.ejb.Local;
import org.aguzman.webapp.ear.ejb.entity.Usuario;

import java.util.List;

//547

//1 - INTERFAZ UsuarioService :  ESTE ES UN EJB. SE PODRÍA LLAMAR UsuarioServiceEjb.
//2 - @Local.

@Local  //2
public interface UsuarioService {

    List<Usuario>listar();

}
