package com.example.taxservice.dto;

import com.example.taxservice.entity.enums.Currency;
import com.example.taxservice.entity.enums.LegalEntityType;
import com.example.taxservice.entity.enums.ReportType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReportDTO {

    @NotNull
    @NotEmpty(message = "{new.report.error.field}")
    @Size(min = 2, max = 30, message = "{new.report.error.company.name}")
    private String companyName;

    private Long id;

    private String comment;

    private boolean editable;

    @NotNull(message = "{new.report.error.enum}")
    private ReportType reportType;

    @NotNull
    @Positive(message = "{new.report.error.negative}")
    @DecimalMax(value = Integer.MAX_VALUE + "", message = "{new.report.error.company.id}")
    private Integer companyId;

    @NotNull
    @NotEmpty(message = "{new.report.error.city}")
    private String city;

//    @NotNull
//    @NotEmpty(message = "{new.report.error.field}")
//    private String date;

    @NotNull(message = "{new.report.error.field}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "{report.validation.date}")
    private LocalDate date;


    @NotNull(message = "{new.report.error.enum}")
    private LegalEntityType legalEntityType;

    @NotNull(message = "{new.report.error.enum}")
    private Currency currency;

    @NotNull
    @PositiveOrZero(message = "{new.report.error.negative}")
    private Long currentAssets;

    @NotNull
    @PositiveOrZero(message = "{new.report.error.negative}")
    private Long nonCurrentAssets;

    @NotNull
    @PositiveOrZero(message = "{new.report.error.negative}")
    private Long currentLiabilities;

    @NotNull
    @PositiveOrZero(message = "{new.report.error.negative}")
    private Long nonCurrentLiabilities;

    @NotNull
    @PositiveOrZero(message = "{new.report.error.negative}")
    private Long equality;
}