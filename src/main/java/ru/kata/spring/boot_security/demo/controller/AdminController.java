package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //Read
    @GetMapping("")
    public String displayUsers(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "users";
    }

    @GetMapping("/{username}")
    @ResponseBody
    public User getUser(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }


    //Create
    @PostMapping("/addNew")
    public String saveUser(@RequestBody User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    //Update
    @PatchMapping("/{username}")
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    //Delete
    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        System.out.println(username);
        userService.deleteUser(username);
        return "redirect:/admin";
    }
}
