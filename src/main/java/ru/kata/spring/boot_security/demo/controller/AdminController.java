package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // all users

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("userList", userService.listUsers());
        model.addAttribute("roleList", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        model.addAttribute("authorisedUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "admin_page";
    }

    // add

    @PostMapping("/")
    public String createNewUser(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    // edit users

    @GetMapping("{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") long id) {
        User userToEdit = userService.getUserById(id);

        String roleUser = (userToEdit.getRoles().contains("ROLE_USER")) ? "on" : null;

        String roleAdmin = (userToEdit.getRoles().contains("ROLE_ADMIN")) ?"on" : null;

        model.addAttribute("userToEdit", userService.getUserById(id));
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("roleAdmin", roleAdmin);
//        return "adminController/edit";
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    // remove users

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    // show user by id

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "user_info_by_id";
    }

}
