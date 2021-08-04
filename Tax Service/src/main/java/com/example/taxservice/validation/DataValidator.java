package com.example.taxservice.validation;

import com.example.taxservice.dto.UserDTO;
import com.example.taxservice.entity.Currency;
import com.example.taxservice.entity.LegalEntityType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator {

    public LocalDate validateDate(String date) throws DateTimeParseException{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate userBirthDate = LocalDate.parse(date, formatter);
            return userBirthDate;
        }
}