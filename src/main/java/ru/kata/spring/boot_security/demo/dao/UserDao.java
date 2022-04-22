package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserDao {

    public User getUserByEmail(String email);

    public void addUser(User user);

    public User getUserById(long id);

    public User updateUser(User user);

    public void removeUserById(long id);

    public List<User> listUsers();
}