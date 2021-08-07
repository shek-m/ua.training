package com.example.taxservice.controller;

import com.example.taxservice.dto.UserDTO;
import com.example.taxservice.entity.User;
import com.example.taxservice.service.UserService;
import com.example.taxservice.service.exceptions.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeParseException;

@Slf4j
@RestController
public class RegFormController {
    private UserService userservice;

    @Autowired
    public RegFormController(UserService userservice) {
        this.userservice = userservice;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("user") UserDTO userDto, ModelAndView mov) {

        mov.setViewName("registration");

        try{
            User registered = userservice.registerNewAccountUser(userDto);
        } catch(UserAlreadyExistException ex){
            mov.addObject("message", "An account for that username/email already exists.");
            return mov;
        } catch(DateTimeParseException ex){
            mov.addObject("message", "Input date format is not appropriate.");
            return mov;
        }

        log.info("{}", userDto);

        return new ModelAndView("successRegister", "user", userDto);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}