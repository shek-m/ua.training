package com.example.taxservice.entity.enums;

import java.util.Locale;

public enum ReportStatus {
    PROCESSING("В ОБРОБЦІ"),
    SUBMITTED("ПРИЙНЯТО"),
    DENIED("ВІДХИЛЕНО");

    ReportStatus(String ukName) {
        this.ukName = ukName;
    }

    private String ukName;

    public String getLocaleName(Locale locale) {
        return (!locale.getLanguage().equals(Locale.US.getLanguage())) ?
                this.ukName : this.name();
    }
}