package jee.master.models.service;

import jee.master.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public User getUserByUsername(String username);

    public Optional<User> login(String username, String password);

    public List<User> userList();

    public  Optional <User> getUserById(Long id);

    public void saveUser(User user);

    public void deleteById(Long id);

}
