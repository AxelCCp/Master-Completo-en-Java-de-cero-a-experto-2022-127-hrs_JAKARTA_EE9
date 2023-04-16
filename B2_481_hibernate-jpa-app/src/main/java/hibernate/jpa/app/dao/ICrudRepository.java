package hibernate.jpa.app.dao;

import java.util.List;

public interface ICrudRepository <T> {

    List<T> listar();
    T porId(Long id);
    void guardar(T t);
    void eliminar(Long id);

}
