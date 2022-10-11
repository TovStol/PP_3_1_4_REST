package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;

    }



    @GetMapping(value = "/users")
    public String getUser(Model model, Principal principal) {
        model.addAttribute("listUser", userService.getUserByName(principal.getName()));
        return "users";
    }

    @GetMapping(value = "/admins")
    public String getAdmin(Model model) {
        model.addAttribute("listUser", userService.getAllUsers());
        return "admins";
    }

    @GetMapping(value = "/admins/new")
    public String addUser(Model model) {
        User user = new User();
        List<Role> role = roleService.getAllRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", role);
        return "add_user";
    }

    @PostMapping(value = "/admins")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admins";
    }

    @GetMapping(value = "admins/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<Role> role = roleService.getAllRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", role);
        return "edit_user";
    }


    @PostMapping(value = "/admins/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        userService.updateUser(id, user);
        return "redirect:/admins";
    }

    @DeleteMapping(value = "admins/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.removeUserById(id);
        return "redirect:/admins";
    }

}
