package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserDao {
    void saveUser(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    boolean updateUser(User user);

    User getUserById(Long id);

    User getUserByName(String name);
    User getUserByEmail(String email);
}
