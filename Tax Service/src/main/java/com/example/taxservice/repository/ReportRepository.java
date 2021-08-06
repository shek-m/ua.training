package com.example.taxservice.repository;

import com.example.taxservice.entity.Report;
import com.example.taxservice.entity.enums.ReportStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByUserId(Long id);

    @Override
    Optional<Report> findById(Long id);

    List<Report> findByStatus(ReportStatus statusVal);
}