package com.example.taxservice.controller;

import com.example.taxservice.entity.User;
import com.example.taxservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping("/index")
    public String viewHomePage(Model model){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getRole());
        return "index";
    }
    @RequestMapping("/login")
    public String getLogin(@RequestParam(value="error", required=false) String error,
                           @RequestParam(value="logout", required=false) String logout,
                           Model model){
        model.addAttribute("error", error !=null);
        model.addAttribute("logout", logout !=null);

        return "login";
    }


    @RequestMapping("/register")
    public String registrationPage() { return "reg_form";}

    @GetMapping("/")
    public String welcomePage() { return "welcome";}
}