package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    //Transactional(readOnly = true)
    @Override
    @Transactional
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        return null;
    }

    @Override
    @Transactional
    public User edit(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }


    //изменён на обёртку в связи с последнем замечанием в 2.3.1 исправил

    @Override
    @Transactional
    public void delete(Long id) {
        User existingUser = (User) userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Пользователь не найден с помощью метода удаления в классе UserServiceImp"));
        userRepository.delete(existingUser);
    }
    //Transactional(readOnly = true)
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
    //Transactional(readOnly = true)
    //изменён на обёртку в связи с последнем замечанием в 2.3.1

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return (User) userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Пользователь с таким id не найден в классе UserServiceImp"));
    }
    //Transactional(readOnly = true)
    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String email) {

        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
