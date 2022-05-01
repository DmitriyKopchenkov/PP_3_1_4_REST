package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.exception_handling.UserIncorrectData;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    // all user

    @GetMapping
    public List<User> showAllUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public User showUserToId(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("Нет пользователя с ID = " + id);
        }
        return user;
    }

    @PostMapping
    public List<User> createNewUser(@RequestBody User user) {
        userService.addUser(user);
        return userService.listUsers();
    }

    @PutMapping
    public List<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return userService.listUsers();
    }

    @DeleteMapping("{id}")
    public List<User> deleteUserById(@PathVariable("id") long id) {
        if (userService.getUserById(id) == null) {
            throw new NoSuchUserException("Нет пользователя с ID = " + id);
        }
        userService.removeUserById(id);
        return userService.listUsers();
    }
}
