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
        List<User> allUsers = userService.listUsers();
        return allUsers;
    }

    @GetMapping("/{id}")
    public User showUserToId(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("Нет пользователя с ID = " + id);
        }
        return user;
    }

    @ExceptionHandler // метод ответственный за обработку исключений (неверный id)
    public ResponseEntity<UserIncorrectData> handleException(NoSuchUserException exception) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // метод ответственный за обработку исключений (остальные исключения)
    public ResponseEntity<UserIncorrectData> handleException(Exception exception) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


//    // add user
//
//    @PostMapping
//    public String createNewUser(@ModelAttribute("user") User user,
//                                @RequestParam(value = "nameRoles") String[] roles) {
//        user.setRoles(roleService.getSetOfRoles(roles));
//        userService.addUser(user);
//        return "redirect:/admin/";
//    }
//
//    // edit users
//
//    @GetMapping("{id}/edit")
//    public String editUserForm(@ModelAttribute("user") User user,
//                               ModelMap model,
//                               @PathVariable("id") long id,
//                               @RequestParam(value = "editRoles") String[] roles) {
//        user.setRoles(roleService.getSetOfRoles(roles));
//        model.addAttribute("roles", roleService.getAllRoles());
//        model.addAttribute("user", userService.getUserById(id));
//        return "admin";
//    }
//
//    @PostMapping("/{id}")
//    public String update(@ModelAttribute("user") User user,
//                         @PathVariable("id") long id,
//                         @RequestParam(value = "editRoles") String[] roles) {
//        user.setRoles(roleService.getSetOfRoles(roles));
//        userService.updateUser(user);
//        return "redirect:/admin/";
//    }
//
//    // remove users
//
//    @GetMapping("/{id}/remove")
//    public String deleteUserById(@PathVariable("id") long id) {
//        userService.removeUserById(id);
//        return "redirect:/admin/";
//    }
}
