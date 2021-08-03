package com.example.taxservice.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name="user_id", nullable = false)
    private Long user_id;

    @Column(name="company_name", nullable = false)
    private String companyName;

    @Column(name="company_id", nullable = false)
    private Integer companyId;

    @Column(name="city", nullable = false)
    private String city;

    @Column(name="date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Column(name="legal_entity_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LegalEntityType legalEntityType;

    @Column(name="currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="current_assets", nullable = false)
    private Long currentAssets;

    @Column(name="non_current_assets", nullable = false)
    private Long nonCurrentAssets;

    @Column(name="total_assets", nullable = false)
    private Long totalAssets = currentAssets + nonCurrentAssets;

    @Column(name="current_liabilities", nullable = false)
    private Long currentLiabilities;

    @Column(name="non_current_liabilities", nullable = false)
    private Long nonCurrentLiabilities;

    @Column(name="total_liabilities", nullable = false)
    private Long totalLiabilities = currentLiabilities + nonCurrentLiabilities;

    @Column(name="equality", nullable = false)
    private Long equality;

    @Column(name = "status")
    private ReportStatus status;

    @Column(name = "comment")
    private String comment;
}