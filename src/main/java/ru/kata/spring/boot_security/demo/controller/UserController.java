package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String toLogin() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admen", user);
        model.addAttribute("mans", userService.getAllUsers());
        model.addAttribute("userRoles", userService.getRoles());
        User emptyUser = new User();
        model.addAttribute("emptyUser", emptyUser);
        return "admin";
    }

    @GetMapping("/user")
    public String showUserForUser (Model userModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userModel.addAttribute("man", user);
        return "user";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute("emptyUser") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }
}
