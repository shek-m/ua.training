package com.example.taxservice.repository;

import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.enums.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByUserId(Long id);

    Page<Report> findAll(Pageable pageable);

    @Override
    Optional<Report> findById(Long id);

    List<Report> findByReportStatusAndUserId(ReportStatus statusVal, Long id);

    List<Report> findByUserIdOrderByDateDesc(Long id);

    List<Report> findByUserIdOrderByDateAsc(Long id);

    List<Report> findByUserIdOrderByReportTypeDesc(Long id);

    List<Report> findByUserIdOrderByReportTypeAsc(Long id);

    List<Report> findByReportStatusAndUserIdOrderByDateDesc(ReportStatus statusVal, Long id);
}