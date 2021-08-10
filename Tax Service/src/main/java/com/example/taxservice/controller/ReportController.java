package com.example.taxservice.controller;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.dto.ReportFilterDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.User;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

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

    @PostMapping("/admin/reviewed")
    public ModelAndView reviewReport(@ModelAttribute("report") Report report, ModelAndView mov) {
        reportService.reviewReportByAdmin(report);
        mov.setViewName("successReportReviewed");
        return mov;
    }

    @PostMapping("/admin/page/{id}")
    public ModelAndView filterReports(@ModelAttribute("dto") ReportFilterDTO dto, ModelAndView mov,
                                                        @PathVariable(name = "id") int pageNum) {
            mov.setViewName("admin/reports");
            String sortDir = "desc";
            String sortField = "date";

            Page<Report> page = reportService.getFilteredPagedReports(pageNum, sortField,
                    sortDir, dto.getReportType(), dto.getUserid());

            List<Report> reports = page.getContent();

            mov.addObject("currentPage", pageNum);
            mov.addObject("totalPages", page.getTotalPages());
            mov.addObject("totalItems", page.getTotalElements());

            mov.addObject("sortField", sortField);
            mov.addObject("sortDir", sortDir);
            mov.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
            mov.addObject("reports", reports);
            mov.addObject("dto", new ReportFilterDTO());
        return mov;
    }

    @PostMapping("/user/reports/{id}")
    public ModelAndView filterByStatus(@PathVariable Long id, ReportFilterDTO dto, ModelAndView mov,
                                       HttpServletRequest request, SessionLocaleResolver slr) {
        mov.setViewName("user/reports");
        if (dto.getReportStatus() == null) {
            mov.addObject("reports", reportService.listUserReports(id));
        } else {
            mov.addObject("reports", reportService.filterUserReportsByStatus(dto.getReportStatus(), id));
        }
        mov.addObject("statusDTO", new ReportFilterDTO());
        mov.addObject("loc", slr.resolveLocale(request));
        return mov;
    }

    @ModelAttribute(name = "userID")
    public Long getAuthUserId(@AuthenticationPrincipal User user) {
        return user.getId();
    }
    @ModelAttribute("loc")
    public Locale getCurrentLocale(HttpServletRequest request,
                                   SessionLocaleResolver slr) {
        return slr.resolveLocale(request);
    }
}