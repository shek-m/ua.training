package com.example.taxservice.controller;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.dto.ReportStatusDTO;
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
import java.util.List;

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

        try {
            Report addedReport = reportService.addNewReport(reportDto);
            userID = addedReport.getUser().getId();
        } catch (DateTimeParseException ex) {
            mov.addObject("message", "Input date format is not appropriate.");
            return mov;
        }

        log.info("{}", reportDto);
        ModelAndView mov2 = new ModelAndView("successReportAdded");
        mov2.addObject("userID", userID);

        return new ModelAndView("successReportAdded", "userID", userID);
    }

    @PostMapping("/admin/checked")
    public ModelAndView reviewReport(@ModelAttribute("report") @Valid Report report) {
        ModelAndView modelAndView = new ModelAndView("admin/reports");
        try {
            Report reviewedReport = reportService.reviewReportByAdmin(report);
        } catch (DateTimeParseException ex) {
            modelAndView.addObject("message", "Input date format is not appropriate.");
            return new ModelAndView("admin/report_review_form");
        }
        log.info("{}", report);
        modelAndView.addObject("message", "Report was reviewed");
        modelAndView.addObject("reports", reportService.listAllReports());
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/user/reports/{id}/filter-by-status")
    public ModelAndView filterByStatus(@PathVariable Long id,@ModelAttribute("dto") ReportStatusDTO dto, ModelAndView modelAndView) {
        modelAndView.setViewName("user/reports");
        List<Report> filteredByStatus = reportService.filterUserReportsByStatus(dto.getReportStatus());

        System.out.println(dto.getReportStatus().name());
        filteredByStatus.forEach(System.out::println);

        modelAndView.addObject("reports", filteredByStatus);
        modelAndView.addObject("statusDTO", new ReportStatusDTO());
        modelAndView.addObject("userID", id);

        System.out.println("отработал");
        return modelAndView;
    }
}