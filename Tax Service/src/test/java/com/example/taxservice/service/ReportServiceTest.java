package com.example.taxservice.service;

import com.example.taxservice.dto.ReportDTO;
import com.example.taxservice.entity.Report;
import com.example.taxservice.repository.ReportRepository;
import com.example.taxservice.service.exceptions.ReportNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;

    @MockBean
    private ReportRepository reportRepository;

    @Test
    public void testGetById() throws ReportNotFoundException {
        Report report = mock(Report.class);
        Optional<Report> reportOptional = Optional.of(report);
        when(reportRepository.findById(anyLong())).thenReturn(reportOptional);
        Report result = reportService.getById(10L);
        Assert.assertEquals("result", result, report);
    }

    @Test(expected = ReportNotFoundException.class)
    public void testGetById_ReportIsAbsent() throws ReportNotFoundException {
        when(reportRepository.findById(anyLong())).thenThrow(ReportNotFoundException.class);
        reportService.getById(10L);
    }

    @Test
    public void testAddNewReport() {
        ReportDTO dto = mock(ReportDTO.class);
        Report report = mock(Report.class);
        when(reportRepository.save(Mockito.any(Report.class))).thenReturn(report);
        Report result = reportService.addNewReport(dto);
        System.out.println(result);
        System.out.println(report);
        Assert.assertEquals("result", result, report);
    }
}