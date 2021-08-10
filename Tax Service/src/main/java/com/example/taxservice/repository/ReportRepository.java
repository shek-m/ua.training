package com.example.taxservice.repository;

import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.enums.ReportStatus;
import com.example.taxservice.entity.enums.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByUserId(Long id);

    Page<Report> findByUserId(Long id, Pageable pageable);

    Page<Report> findAll(Pageable pageable);

    Page<Report> findByReportTypeAndUserId(ReportType type, Long id, Pageable pageable);

    Page<Report> findByReportType(ReportType type, Pageable pageable);

    @Override
    Optional<Report> findById(Long id);

    List<Report> findByReportStatusAndUserId(ReportStatus statusVal, Long id);

    List<Report> findByReportStatus(ReportStatus statusVal);

    List<Report> findByUserIdOrderByDateDesc(Long id);

    List<Report> findByUserIdOrderByDateAsc(Long id);

    List<Report> findByUserIdOrderByReportTypeDesc(Long id);

    List<Report> findByUserIdOrderByReportTypeAsc(Long id);

    Long countByReportStatus(ReportStatus status);

    long count();

    Long countByDateBetween(LocalDate d1, LocalDate d2);

    List<Report> findByReportStatusAndUserIdOrderByDateDesc(ReportStatus statusVal, Long id);
}