package jee.master.models.dao;

import jee.master.models.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements IUserRepository{

    public UserRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public List list() throws SQLException {
        return null;
    }

    @Override
    public Object getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(Object o) throws SQLException {

    }

    @Override
    public void deleteById(Long id) throws SQLException {

    }

    @Override
    public User findByUsername(String username) throws SQLException {
        User user = null;
        try(PreparedStatement pst = connection.prepareStatement("select * from users where username = ?")){
            pst.setString(1, username);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                }
            }
        }
        return user;
    }

    Connection connection;
}
