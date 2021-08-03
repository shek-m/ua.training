package com.example.taxservice.validation;

import com.example.taxservice.dto.UserDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegistrationFormDataValidator {

    public LocalDate validateDate(UserDTO userDto) throws DateTimeParseException{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate userBirthDate = LocalDate.parse(userDto.getDate(), formatter);
            return userBirthDate;
        }
    }
