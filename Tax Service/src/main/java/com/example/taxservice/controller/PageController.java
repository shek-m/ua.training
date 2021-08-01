package com.example.taxservice.controller;

import com.example.taxservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("user", userService.getActiveUser());
        return "index";
    }

    @RequestMapping("/register")
    public String registrationPage() { return "reg_form";}
}