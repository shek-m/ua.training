package com.example.taxservice.dto;

import com.example.taxservice.entity.enums.ReportStatus;
import com.example.taxservice.entity.enums.ReportType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportFilterDTO {
    private ReportStatus reportStatus;
    private ReportType reportType;
    private Long userid;
}
