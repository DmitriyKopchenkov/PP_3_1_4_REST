package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRep;

import java.util.List;
@Service ("userEntityServiceImp")
public class UserServiceImp implements UserService{
    private final UserRep userRep;
    @Autowired
    public UserServiceImp(UserRep userRep) {
        this.userRep = userRep;
    }

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRep.save(user);
    }

    @Override
    public User update(User user, int id) {
        User existingUser = userRep.findById(id).orElseThrow(
                ()-> new RuntimeException("User is not found with update method in UserServiceImp class"));
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());
        return userRep.save(existingUser);
    }

    @Override
    public void delete(int id) {
        User existingUser = userRep.findById(id).orElseThrow(
                ()-> new RuntimeException("User is not found with update method in UserServiceImp class"));
        userRep.delete(existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRep.findAll();
    }

    @Override
    public User getById(int id) {
        return userRep.findById(id).orElseThrow(
                ()-> new RuntimeException("User is not found with update method in UserServiceImp class"));
    }

    @Override
    public User findByUsername(String email) {
        return userRep.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRep.getUserByEmail(email);
        user.getAuthorities().size();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return user.fromUser();
    }
}
