package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
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
    public String getUser(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "users";
    }
    @GetMapping(value = "/admins")
    public String getAdmin(Model model,Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        model.addAttribute("listUser", userService.getAllUsers());
        model.addAttribute("listRole", roleServiceImpl.getAllRole());
        return "admins";
    }
    @GetMapping(value = "/admins/new")
    public String addUser(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        User newUser = new User();
        List<Role> role = roleServiceImpl.getAllRole();
        model.addAttribute("newUser", newUser);
        model.addAttribute("listRole", role);
        return "add_user";
    }
    @PostMapping(value = "/admins")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.saveUser(user);
        return "redirect:/admins";
    }
    @PostMapping(value = "/admins/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admins";
    }
    @PostMapping(value = "/admins/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        userService.updateUser(id, user);
        return "redirect:/admins";
    }
}
