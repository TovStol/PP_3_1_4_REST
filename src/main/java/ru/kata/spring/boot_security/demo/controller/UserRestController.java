package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;
    private final RoleServiceImpl roleServiceImpl;

    public UserRestController(UserService userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    public User getUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping(value = "/users/{id}")
    public User getUsersById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User user) {
        System.out.println(user.getRoles());
        userService.saveUser(user);
        return user;
    }

    @PatchMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user.getId(), user);
        return user;
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "user with id = " + id + " was delete";
    }

}
