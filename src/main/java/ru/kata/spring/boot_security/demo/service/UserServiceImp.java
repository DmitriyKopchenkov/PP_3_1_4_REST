package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.List;

@Service ("userServiceImp")
@AllArgsConstructor
@Transactional
public class UserServiceImp implements UserService {
    //заинжетил модификатор доступа BCryptPasswordEncoder
// так же убрал конструктор создания new и заавтоваирил.

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(12);
    }
    //Transactional(readOnly = true)
    @Override
    @Transactional(readOnly = true)
    public User add(User user) {
        return userRepository.save(user);
    }
    //Transactional(readOnly = true)
    //изменён на обёртку в связи с последнем замечанием в 2.3.1
    @Override
    @Transactional(readOnly = true)
    public User update(User user, Long id) {
        User existingUser = (User) userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Пользователь не найден с помощью метода обновления в классе UserServiceImp"));
        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    //Transactional(readOnly = true)
    //изменён на обёртку в связи с последнем замечанием в 2.3.1 исправил

    @Override
    @Transactional(readOnly = true)
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
    //Transactional(readOnly = true)
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        user.getAuthorities().size();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь с email = '%s' не найден", email));
        }
        return user.fromUser();
    }
}
