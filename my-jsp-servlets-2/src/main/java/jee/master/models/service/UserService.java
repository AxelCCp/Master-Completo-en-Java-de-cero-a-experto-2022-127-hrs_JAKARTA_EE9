package jee.master.models.service;

import jee.master.models.dao.IUserRepository;
import jee.master.models.dao.UserRepository;
import jee.master.models.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

    @Override
    public List<User> userList() {
        try {
            return userRepository.list();
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            return Optional.ofNullable((User) userRepository.getById(id));
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(),e.getCause());
        }
    }

    @Override
    public void saveUser(User user) {
        try {
            userRepository.save(user);
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (SQLException e) {
            throw new ExceptionJdbcService(e.getMessage(),e.getCause());
        }
    }


    private IUserRepository userRepository;
}
