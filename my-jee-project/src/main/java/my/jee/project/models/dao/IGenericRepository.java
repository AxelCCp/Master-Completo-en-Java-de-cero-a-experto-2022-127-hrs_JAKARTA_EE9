package my.jee.project.models.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGenericRepository <T> {
    public List<T> list() throws SQLException;
    public T getById(Long id) throws SQLException;
    public void save(T t) throws SQLException;
    public void deleteById(Long id) throws SQLException;
}
