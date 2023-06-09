package jee.master.models.dao;

import jee.master.models.entity.User;

import java.sql.SQLException;

public interface IUserRepository extends IGenericRepository <User>{

    public User findByUsername(String username) throws SQLException;

}
