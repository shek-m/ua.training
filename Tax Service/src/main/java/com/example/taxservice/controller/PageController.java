package com.example.taxservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String viewHomePage(){
        return "index";
    }

    @RequestMapping("/register")
    public String registrationPage() { return "reg_form";}


}