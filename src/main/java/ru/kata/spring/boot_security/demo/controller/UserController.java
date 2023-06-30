package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ModelAndView singleUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("currentUser");
        return modelAndView;
    }

}
