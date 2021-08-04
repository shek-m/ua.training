package com.example.taxservice.controller;

import com.example.taxservice.entity.Report;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public AdminController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/admin/home")
    public String userHomePage() {
        return "admin/adminMain";
    }

    @GetMapping("/admin/reports")
    public String getReportList(Model model) {
        List<Report> list = reportService.listAllReports();
        model.addAttribute("reports", list);
        return "admin/reports";
    }
}