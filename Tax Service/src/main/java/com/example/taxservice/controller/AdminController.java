package com.example.taxservice.controller;

import com.example.taxservice.entity.Report;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import com.example.taxservice.service.exceptions.ReportNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public AdminController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String userHomePage() {
        return "admin/adminMain";
    }

    @GetMapping("/reports")
    public String getReportList(Model model) {
        return viewPage(model, 1);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum) {
        Page<Report> page = reportService.getAllPagedReports(pageNum);
        List<Report> reports = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reports", reports);

        return "admin/reports";
    }


    @GetMapping("/reports/{id}")
    public String reviewReport(@PathVariable Long id, Model model) {
        Report report = null;
        try {
            report = reportService.getById(id);
        } catch (ReportNotFoundException e) {
            log.error(e.getMessage());
            return "admin/reports";
        }
        model.addAttribute("report", report);
        return "admin/report_review_form";
    }
}