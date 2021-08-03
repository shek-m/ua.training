package com.example.taxservice.controller;

import com.example.taxservice.dto.UserDTO;
import com.example.taxservice.entity.Role;
import com.example.taxservice.entity.User;
import com.example.taxservice.repository.UserRepository;
import com.example.taxservice.service.RegFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class RegFormController {
    private final RegFormService regFormService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RegFormController(RegFormService regFormService) {
        this.regFormService = regFormService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent()){
            model.addAttribute("user_exists", "User exists!");
            return "registration";
        }
        user.setRole(Role.USER);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(user);

        log.info("{}", user);

        return "login";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}