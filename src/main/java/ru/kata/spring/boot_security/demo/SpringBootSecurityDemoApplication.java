package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		try {
			UserService userService = context.getBean(UserService.class);
			RoleService roleService = context.getBean(RoleService.class);
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
			User user1 = new User("admin", "admin", "admin@mail.ru"
					, bCryptPasswordEncoder.encode("admin"));
			Role roleAdmin = new Role("ADMIN");
			Role roleUser = new Role("USER");
			Set<Role> roles = new HashSet<>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			user1.setRoles(roles);
			roleService.add(roleAdmin);
			roleService.add(roleUser);
			userService.addUser(user1);
		} catch (Exception ignored) {
		}
	}

}
