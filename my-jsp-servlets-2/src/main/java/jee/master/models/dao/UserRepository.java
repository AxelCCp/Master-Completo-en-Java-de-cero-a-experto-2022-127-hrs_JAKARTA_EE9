package jee.master.models.dao;

import jee.master.models.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository{

    public UserRepository(Connection connection) {
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
    public void save(User user) throws SQLException {
        String sql;
        if(user.getId() != null && user.getId() > 0){
            sql = "update users set username=?, password=?, email=? where id=?";
        } else {
            sql = "insert into users (username, password, email) values (?,?,?)";
        }
        try(PreparedStatement st = connection.prepareStatement(sql)){
            if(user.getId() != null && user.getId() > 0){
                st.setLong(4, user.getId());
            }
            st.setString(1, user.getUsername());
            st.setString(2,user.getPassword());
            st.setString(3, user.getEmail());
            st.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "delete from users where id=?";
        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setLong(1, id);
            pst.executeUpdate();
        }
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
