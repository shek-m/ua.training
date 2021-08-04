package com.example.taxservice.controller;


import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public UserController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/user/home")
    public String userHomePage(Model model){
        Long id = userService.getUserID();
        model.addAttribute("userID", id);

        return "user/userMain";
    }

    @GetMapping("/user/reports/{id}")
    public String getReportList(@PathVariable Long id,  Model model) {
       // id = userService.getUserID();
        Long finalId = userService.getUserID();

        List<Report> list = reportService.listAllReports().stream().filter(r -> r.getUser_id().equals(finalId)).collect(Collectors.toList());
        model.addAttribute("reports", list);
      //  model.addAttribute("userID", id);

        return "user/reports";
    }

    @GetMapping("/user/new-report")
    public String addNewReport(Model model) {
        ReportDTO reportDto = new ReportDTO();
        model.addAttribute("report", reportDto);
        return "user/report_form";
    }
}