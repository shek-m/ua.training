package com.example.taxservice.dto;

import com.example.taxservice.entity.enums.Currency;
import com.example.taxservice.entity.enums.LegalEntityType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReportDTO {

    @NotNull
    @NotEmpty
    private String companyName;

    @NotNull
    private Integer companyId;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String date;

    @NotNull
    private LegalEntityType legalEntityType;

    @NotNull
    private Currency currency;

    @NotNull
    private Long currentAssets;

    @NotNull
    private Long nonCurrentAssets;

    @NotNull
    private Long currentLiabilities;

    @NotNull
    private Long nonCurrentLiabilities;


    @NotNull
    private Long equality;
}