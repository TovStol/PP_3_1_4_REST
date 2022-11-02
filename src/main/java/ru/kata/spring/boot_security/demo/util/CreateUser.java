package ru.kata.spring.boot_security.demo.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class CreateUser {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleServiceImpl roleService;


    public CreateUser(PasswordEncoder passwordEncoder, UserService userService, RoleServiceImpl roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void addUserInDB() {
        Set<Role> userRole = new HashSet<>();
        Set<Role> adminRole = new HashSet<>();
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        userRole.add(role1);
        adminRole.add(role1);
        adminRole.add(role2);

        User user1 = new User("user1", "user1", (byte) 33, "user1@mail.ru",
                passwordEncoder.encode("user1"));

        user1.setRoles(adminRole);
        userService.saveUser(user1);

        User user2 = new User("user2", "user2", (byte) 33, "user2@mail.ru",
                passwordEncoder.encode("user2"));
        user2.setRoles(userRole);
        userService.saveUser(user2);

    }
}
