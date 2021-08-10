package com.example.taxservice.controller;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.dto.ReportStatusDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.User;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
public class ReportController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public ReportController(ReportService reportService, UserService service) {
        this.reportService = reportService;
        this.userService = service;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/new-report")
    public ModelAndView addReport(@ModelAttribute("report") @Valid ReportDTO reportDto,
                                  BindingResult bindingResult, HttpServletRequest request, ModelAndView mov,
                                SessionLocaleResolver slr) {
        mov.setViewName("user/report_form");
        mov.addObject("loc", slr.resolveLocale(request));

        if (bindingResult.hasErrors()) {
            return mov;
        }

        Report addedReport = reportService.addNewReport(reportDto);

        log.info("{}", reportDto);
        return new ModelAndView("successReportAdded");
    }

    @PostMapping("/admin/reports")
    public ModelAndView reviewReport(@ModelAttribute("report") Report report, ModelAndView mov) {
        reportService.reviewReportByAdmin(report);

        log.info("{}", report);
        mov.setViewName("admin/reports");
        mov.addObject("message", "Report was reviewed");
        mov.addObject("reports", reportService.listAllReports());
        return mov;
    }

    @PostMapping("/user/reports/{id}")
    public ModelAndView filterByStatus(@PathVariable Long id, ReportStatusDTO dto, ModelAndView mov,
                                       HttpServletRequest request, SessionLocaleResolver slr) {
        mov.setViewName("user/reports");
        if (dto.getReportStatus() == null) {
            mov.addObject("reports", reportService.listUserReports(id));
        } else {
            mov.addObject("reports", reportService.filterUserReportsByStatus(dto.getReportStatus(), id));
        }
        mov.addObject("statusDTO", new ReportStatusDTO());
        mov.addObject("loc", slr.resolveLocale(request));
        return mov;
    }

    @ModelAttribute(name = "userID")
    public Long getAuthUserId(@AuthenticationPrincipal User user) {
        return user.getId();
    }
}