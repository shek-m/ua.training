package com.example.taxservice.service;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.enums.ReportStatus;
import com.example.taxservice.repository.ReportRepository;
import com.example.taxservice.service.exceptions.ReportNotFoundException;
import com.example.taxservice.validation.DataValidator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ReportService {

    private ReportRepository reportRepository;
    private UserService userService;

    @Autowired
    public ReportService(ReportRepository reportRepository, UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }

    public List<Report> listAllReports(){
        return (List<Report>) reportRepository.findAll();
    }

    public List<Report> listUserReports(@NonNull Long id) {
        return reportRepository.findByUserId(id);
    }

    public Report getById(@NonNull Long id) throws ReportNotFoundException {
        return reportRepository.findById(id).orElseThrow(() ->
            new ReportNotFoundException("Report with current ID doesn't exist"));}


    public Report reviewReportByAdmin(Report report) throws DateTimeParseException {
        return reportRepository.save(report);
    }


    public List<Report> filterUserReportsByStatus(ReportStatus statusVal, Long id) {
        return reportRepository.findByStatusAndUserId(statusVal, id);
    }

    public List<Report> sortUserReportsByDateDesc(Long id) {
        return reportRepository.findByUserIdOrderByDateDesc(id);
    }

    public List<Report> sortUserReportsByDateAsc(Long id) {
        return reportRepository.findByUserIdOrderByDateAsc(id);
    }

    public List<Report> sortUserReportsByTypeDesc(Long id) {
        return reportRepository.findByUserIdOrderByReportTypeDesc(id);
    }

    public List<Report> sortUserReportsByTypeAsc(Long id) {
        return reportRepository.findByUserIdOrderByReportTypeAsc(id);
    }

    public Report addNewReport(ReportDTO reportDTO) throws IllegalArgumentException, DateTimeParseException {
        DataValidator validator = new DataValidator();
        Report report = Report.builder()
                .user(userService.getUser())
                .id(reportDTO.getId())
                .companyName(reportDTO.getCompanyName())
                .companyId(reportDTO.getCompanyId())
                .city(reportDTO.getCity())
                .date(validator.validateDate(reportDTO.getDate()))
                .legalEntityType(reportDTO.getLegalEntityType())
                .currency(reportDTO.getCurrency())
                .currentAssets(reportDTO.getCurrentAssets())
                .nonCurrentAssets(reportDTO.getNonCurrentAssets())
                .totalAssets((reportDTO.getCurrentAssets() + reportDTO.getNonCurrentAssets()))
                .currentLiabilities(reportDTO.getCurrentLiabilities())
                .nonCurrentLiabilities(reportDTO.getNonCurrentLiabilities())
                .totalLiabilities((reportDTO.getCurrentLiabilities() + reportDTO.getNonCurrentLiabilities()))
                .equality(reportDTO.getEquality())
                .status(ReportStatus.PROCESSING)
                .reportType(reportDTO.getReportType())
                .comment(reportDTO.getComment())
                .editable(false)
                .build();
        return reportRepository.save(report);
    }

    public ReportDTO mappingReportToDto(Report report) {
        return ReportDTO.builder()
                        .id(report.getId())
                        .companyName(report.getCompanyName())
                        .companyId(report.getCompanyId())
                        .city(report.getCity())
                        .date(report.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                        .legalEntityType(report.getLegalEntityType())
                        .currency(report.getCurrency())
                        .currentAssets(report.getCurrentAssets())
                        .nonCurrentAssets(report.getNonCurrentAssets())
                        .currentLiabilities(report.getCurrentLiabilities())
                        .nonCurrentLiabilities(report.getNonCurrentLiabilities())
                        .equality(report.getEquality())
                        .comment(report.getComment())
                        .editable(report.getEditable())
                        .reportType(report.getReportType())
                        .build();
    }
}