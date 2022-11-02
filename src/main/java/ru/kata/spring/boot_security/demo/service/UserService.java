package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
//    void saveUser(User user);

    @Transactional
    void saveUser(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    boolean updateUser(Long id, User user);

    User getUserById(Long id);

    User getUserByName(String name);
    User getUserByEmail(String email);
}
