package com.example.taxservice.service;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.ReportStatus;
import com.example.taxservice.repository.ReportRepository;
import com.example.taxservice.validation.DataValidator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Report addNewReport(ReportDTO reportDTO) throws IllegalArgumentException, DateTimeParseException {
        DataValidator validator = new DataValidator();
        Report report = Report.builder()
                                .user(userService.getUser())
                                .companyName(reportDTO.getCompanyName())
                                .companyId(reportDTO.getCompanyId())
                                .city(reportDTO.getCity())
                                .date(validator.validateDate(reportDTO.getDate()))
                                .legalEntityType(reportDTO.getLegalEntityType())
                                .currency(reportDTO.getCurrency())
                                .currentAssets(reportDTO.getCurrentAssets())
                                .nonCurrentAssets(reportDTO.getNonCurrentAssets())
                                .totalAssets((reportDTO.getCurrentAssets()+ reportDTO.getNonCurrentAssets()))
                                .currentLiabilities(reportDTO.getCurrentLiabilities())
                                .nonCurrentLiabilities(reportDTO.getNonCurrentLiabilities())
                                .totalLiabilities((reportDTO.getCurrentLiabilities()+ reportDTO.getNonCurrentLiabilities()))
                                .equality(reportDTO.getEquality())
                                .status(ReportStatus.PROCESSING)
                                .build();

        return reportRepository.save(report);
    }
}