package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;

@RestController
@RequestMapping ("/api/users")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping()
    public List <User> getAll() {
        return userService.getAllUsers();
    }

//    ResponseEntity-  специальный класс, который представляет http-ответ.
//    Он содержит тело ответа, код состояния, заголовки.
//    Мы можем использовать его для более тонкой настройки http-ответа

    @GetMapping("/{id_var}")
    public ResponseEntity <User> getUserById (@PathVariable ("id_var") int id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser (@RequestBody User user) {
        return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);
    }

    @PutMapping ("/{id_var}")
    public ResponseEntity<User> editUser (@RequestBody User user, @PathVariable ("id_var") int id) {
        return new ResponseEntity<>(userService.update(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id_var}")
    public void deleteUser (@PathVariable ("id_var") int id) {
        userService.delete(id);
    }
}
