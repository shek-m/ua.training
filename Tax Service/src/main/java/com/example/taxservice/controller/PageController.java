package com.example.taxservice.controller;

import com.example.taxservice.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class PageController {

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value="error", required=false) String error,
                           @RequestParam(value="logout", required=false) String logout,
                           Model model){
        model.addAttribute("error", error !=null);
        model.addAttribute("logout", logout !=null);

        return "login";
    }


    @GetMapping("/registration")
    public String registrationPage(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @GetMapping("/")
    public String welcomePage() { return "welcome";}

    @GetMapping("successsegister")
    public String successfulRegistration() {
        return "successRegister";
    }
}