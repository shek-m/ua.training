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
@RequestMapping("/user/")
public class UserController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public UserController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String userHomePage(){
        return "user/userMain";
    }

    @GetMapping("/{id}/reports")
    public String getReportList(@PathVariable Long id,  Model model) {
        id = userService.getUserID();
        List<Report> list = reportService.listAllReports().stream().filter(r -> r.getUser_id().equals(id)).collect(Collectors.toList());
        model.addAttribute("reports", list);
        return "user/reports";
    }
}
