package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public User getUserByEmail(String email);

    public void addUser(User user);

    public User getUserById(int id);

    public void updateUser(User user);

    public void removeUserById(int id);

    public List<User> listUsers();
}