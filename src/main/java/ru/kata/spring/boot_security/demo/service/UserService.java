package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User add (User user);

    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    User update (User user, Long id);

    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    void delete (Long id);
    List<User> getAllUsers();

    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    User getById (Long id);
    User findByUsername (String username);
}
