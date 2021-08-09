package com.example.taxservice.validation;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataValidator {

    public LocalDate validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        if (localDate.isBefore(LocalDate.now()) && localDate.isAfter(LocalDate.ofYearDay(1900, 1))) {
            return localDate;
        } else {
            throw new DateTimeException("{}");
        }
    }
}