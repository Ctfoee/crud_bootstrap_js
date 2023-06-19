package ru.kata.spring.boot_security.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String displayUsers(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "users";
    }

    //Create
    @GetMapping("/users/addNew")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addNew";
    }

    @PostMapping("/users/addNew")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    //Update
    @GetMapping("/users/{username}/update")
    public String updateUserForm(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "update";
    }

    @PatchMapping("/users/{username}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    //Delete
    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(userService.findByUsername(username));
        return "redirect:/admin/users";
    }
}
