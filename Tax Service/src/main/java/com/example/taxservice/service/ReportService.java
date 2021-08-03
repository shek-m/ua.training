package com.example.taxservice.service;

import com.example.taxservice.entity.Report;
import com.example.taxservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> listAllReports(){
        return (List<Report>) reportRepository.findAll();
    }

}
