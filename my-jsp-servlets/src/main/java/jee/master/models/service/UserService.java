package jee.master.models.service;

import jee.master.models.dao.IUserRepository;
import jee.master.models.dao.UserRepository;
import jee.master.models.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserService implements IUserService{

    public UserService(Connection connection){
        this.userRepository = new UserRepository(connection);
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepository.findByUsername(username)).filter(u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }
    }

    private IUserRepository userRepository;
}
