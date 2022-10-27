package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    private final RoleServiceImpl roleServiceImpl;

    public UserController(UserService userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping(value = "/users")
    public String getUser() {
        return "users";
    }

    @GetMapping(value = "/admins")
    public String getAdmin() {
        return "admins";
    }

    @GetMapping(value = "admins/new")
    public String addUser(Model model) {
        List<Role> role = roleServiceImpl.getAllRole();
        model.addAttribute("listRole", role);
        return "addUser";
    }
}
