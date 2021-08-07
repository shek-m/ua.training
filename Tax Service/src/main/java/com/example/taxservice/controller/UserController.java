package com.example.taxservice.controller;


import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.dto.ReportStatusDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.service.ReportService;
import com.example.taxservice.service.UserService;
import com.example.taxservice.service.exceptions.ReportNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
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
        Long id = userService.getUser().getId();
        model.addAttribute("userID", id);

        return "user/userMain";
    }

    @GetMapping("/user/reports/{id}")
    public String getReportList(@PathVariable Long id,
                                @RequestParam(name = "sortby", required = false) String sortBy, Model model) {

        if (sortBy == null) {
            model.addAttribute("reports", reportService.listUserReports(id));
        } else {
            switch (sortBy) {
                case "date-d" : model.addAttribute("reports", reportService.sortUserReportsByDateDesc(id));
                                break;
                case "date-a" : model.addAttribute("reports", reportService.sortUserReportsByDateAsc(id));
            }
        }
        model.addAttribute("statusDTO", new ReportStatusDTO());
        model.addAttribute("userID", id);
        return "user/reports";
    }

    @GetMapping("/user/new-report")
    public String addNewReport(Model model) {
        ReportDTO reportDto = new ReportDTO();
        model.addAttribute("report", reportDto);
        model.addAttribute("userID", userService.getUser().getId());
        model.addAttribute("pageTitle", "Add new report");
        return "user/report_form";
    }

    @GetMapping("/user/reports/{userId}/edit/{id}")
    public String editReport(@PathVariable Long userId, @PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Report report = reportService.getById(id);
            model.addAttribute("report", reportService.mappingReportToDto(report));
            model.addAttribute("userID", id);
            model.addAttribute("pageTitle", "Edit report №" + userId);
            return "user/report_form";

        } catch (ReportNotFoundException ex){
            log.error(ex.getMessage());
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/user/reports";
        }
    }
}