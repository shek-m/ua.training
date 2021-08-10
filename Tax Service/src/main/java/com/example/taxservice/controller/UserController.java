package com.example.taxservice.controller;


import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.dto.ReportFilterDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.User;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import com.example.taxservice.service.exceptions.ReportNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    private ReportService reportService;
    private UserService userService;

    @Autowired
    public UserController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String userHomePage(Model model) {
        return "user/userMain";
    }

    @GetMapping("/reports/{id}")
    public String getReportList(@PathVariable Long id,
                                @RequestParam(name = "sortby", required = true) String sortBy,
                                Model model) {

        List<Report> reports = reportService.listUserReports(id);
        switch (sortBy) {
            case "def":
                break;
            case "date-d":
                reports = reportService.sortUserReportsByDateDesc(id);
                break;
            case "date-a":
                reports = reportService.sortUserReportsByDateAsc(id);
                break;
            case "type-d":
                reports = reportService.sortUserReportsByTypeDesc(id);
                break;
            case "type-a":
                reports = reportService.sortUserReportsByTypeAsc(id);
                break;
        }

        model.addAttribute("reports", reports);
        model.addAttribute("statusDTO", new ReportFilterDTO());
        model.addAttribute("sortBy", sortBy);
        return "user/reports";
    }

    @GetMapping("/new-report")
    public String addNewReport(Model model) {
        model.addAttribute("report", new ReportDTO());
        model.addAttribute("pageTitle", "Add");
        return "user/report_form";
    }

    @GetMapping("/reports/{userId}/edit/{id}")
    public String editReport(@PathVariable Long userId, @PathVariable Long id, Model model,
                             RedirectAttributes ra) {
        try {
            Report report = reportService.getById(id);
            model.addAttribute("report", report);
            model.addAttribute("pageTitle", "Edit");
            return "user/report_form";

        } catch (ReportNotFoundException ex) {
            log.error(ex.getMessage());
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/user/reports";
        }
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