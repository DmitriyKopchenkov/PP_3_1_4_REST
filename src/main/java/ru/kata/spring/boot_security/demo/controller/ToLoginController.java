package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Controller
public class ToLoginController {


    @GetMapping("/")
    public String home() {

        return "redirect:/login";
    }
    public UserDetails loadUserByUsername(String email, UserRepository userRepository) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        user.getAuthorities().size();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с email = '%s' не найден", email));
        }
        return user.fromUser();
    }
}
