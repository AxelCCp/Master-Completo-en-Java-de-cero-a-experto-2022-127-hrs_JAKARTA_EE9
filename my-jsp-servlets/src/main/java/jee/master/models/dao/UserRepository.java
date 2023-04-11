package jee.master.models.dao;

import jee.master.models.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{

    public UserRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<User> list() throws SQLException {
        List<User>users = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from users")){
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User getById(Long id) throws SQLException {
        User user = new User();
        try(PreparedStatement pst = connection.prepareStatement("select * from users where id=?")){
            pst.setLong(1, id);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                }
            }
        }
        return user;
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
