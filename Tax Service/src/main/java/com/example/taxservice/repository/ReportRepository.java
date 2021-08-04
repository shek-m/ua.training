package com.example.taxservice.repository;

import com.example.taxservice.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    List<Report> findByUserId(Long id);
}