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
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private Integer companyId;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LegalEntityType legalEntityType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(nullable = false)
    private Long currentAssets;

    @Column(nullable = false)
    private Long nonCurrentAssets;

    @Column(nullable = false)
    private Long totalAssets;

    @Column(nullable = false)
    private Long currentLiabilities;

    @Column(nullable = false)
    private Long nonCurrentLiabilities;

    @Column(nullable = false)
    private Long totalLiabilities;

    @Column(nullable = false)
    private Long equality;

    @Column
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Column
    private String comment;
}
