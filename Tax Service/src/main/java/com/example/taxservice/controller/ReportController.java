package com.example.taxservice.controller;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.format.DateTimeParseException;

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/new-report")
    public ModelAndView addReport(@ModelAttribute("report") @Valid ReportDTO reportDto, HttpServletRequest request, Errors errors) {

        ModelAndView mov = new ModelAndView("user/report_form");
        Long userID;

        try{
            Report addedReport = reportService.addNewReport(reportDto);
            userID = addedReport.getUser().getId();
        } catch(IllegalArgumentException ex){
            mov.addObject("message", "You input incorrect currency or legal entity type. Try again.");
            return mov;
        } catch(DateTimeParseException ex){
            mov.addObject("message", "Input date format is not appropriate.");
            return mov;
        }

        log.info("{}", reportDto);

        return new ModelAndView("successReportAdded", "userID", userID);
    }
}