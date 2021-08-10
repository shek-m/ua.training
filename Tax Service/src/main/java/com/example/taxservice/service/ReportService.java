package com.example.taxservice.service;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.User;
import com.example.taxservice.entity.enums.ReportStatus;
import com.example.taxservice.entity.enums.ReportType;
import com.example.taxservice.repository.ReportRepository;
import com.example.taxservice.service.exceptions.ReportNotFoundException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    public Page<Report> getAllPagedReports(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending());
        return reportRepository.findAll(pageable);
    }

    public Page<Report> getFilteredPagedReports(int pageNum, String sortField, String sortDir, ReportType type, Long userId) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());

        return (type == null) ? (userId == null || !userService.isUserPresent(userId)) ? reportRepository.findAll(pageable)
                : reportRepository.findByUserId(userId, pageable) : (userId == null || !userService.isUserPresent(userId))
                ? reportRepository.findByReportType(type, pageable)
                : reportRepository.findByReportTypeAndUserId(type, userId, pageable);
    }

    public List<Report> listUserReports(@NonNull Long id) {
        return reportRepository.findByUserId(id);
    }

    public Report getById(@NonNull Long id) throws ReportNotFoundException {
        return reportRepository.findById(id).orElseThrow(() ->
            new ReportNotFoundException("Report with current ID doesn't exist"));}


    public Report reviewReportByAdmin(Report report) {
        return reportRepository.save(report);
    }


    public List<Report> filterUserReportsByStatus(ReportStatus statusVal, Long id) {
        return reportRepository.findByReportStatusAndUserId(statusVal, id);
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

    public Report addNewReport(ReportDTO reportDTO) {
        return reportRepository.save(Report.builder()
                .user(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()))
                .id(reportDTO.getId())
                .companyName(reportDTO.getCompanyName())
                .companyId(reportDTO.getCompanyId())
                .city(reportDTO.getCity())
                .date(reportDTO.getDate())
                .legalEntityType(reportDTO.getLegalEntityType())
                .currency(reportDTO.getCurrency())
                .currentAssets(reportDTO.getCurrentAssets())
                .nonCurrentAssets(reportDTO.getNonCurrentAssets())
                .totalAssets((reportDTO.getCurrentAssets() + reportDTO.getNonCurrentAssets()))
                .currentLiabilities(reportDTO.getCurrentLiabilities())
                .nonCurrentLiabilities(reportDTO.getNonCurrentLiabilities())
                .totalLiabilities((reportDTO.getCurrentLiabilities() + reportDTO.getNonCurrentLiabilities()))
                .equality(reportDTO.getEquality())
                .reportStatus(ReportStatus.PROCESSING)
                .reportType(reportDTO.getReportType())
                .comment(reportDTO.getComment())
                .editable(false)
                .build());
    }
}