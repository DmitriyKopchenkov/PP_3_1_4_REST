package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

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

    @GetMapping()
    public String homeAdmin() {
        return "redirect:/admin/users";
    }

    // all users

    @GetMapping("users")
    public String printUsers(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "all_users";
    }

    // add

    @GetMapping(value = "users/add")
    public String newUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add_user";
    }

    @PostMapping(value = "users/add")
    public String createNewUser(@ModelAttribute("user") @Valid User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    // edit users

    @GetMapping("users/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    // remove users

    @GetMapping("/{id}/remove")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/";
    }

}
