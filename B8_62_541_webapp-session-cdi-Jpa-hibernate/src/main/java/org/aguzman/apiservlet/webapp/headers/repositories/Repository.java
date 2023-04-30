package org.aguzman.apiservlet.webapp.headers.repositories;

import java.util.List;

public interface Repository<T> {
    List<T> listar() throws Exception;
    T porId(Long id) throws Exception;
    void guardar(T t) throws Exception;
    void eliminar(Long id) throws Exception;
}
