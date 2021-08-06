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

    @ResponseStatus(HttpStatus.FOUND)
    @PostMapping("/user/reports/{id}/filter-by-status")
    public ModelAndView filterByStatus(@PathVariable Long id, @ModelAttribute("dto") ReportStatusDTO dto,
                                       @RequestParam(name = "sortby", required = false) String sortBy,
                                       ModelAndView mov) {
        mov.setViewName("user/reports");

        if (sortBy == null) {
            mov.addObject("reports", reportService.filterUserReportsByStatus(dto.getReportStatus(), id));
        } else {
            switch (sortBy) {
                case "date" : mov.addObject("reports",
                        reportService.filterUserReportsByStatusAndSortByDate(dto.getReportStatus(), id));
            }
        }

        mov.addObject("statusDTO", new ReportStatusDTO());
        mov.addObject("userID", id);
        return mov;
    }
}