package com.example.taxservice.entity.enums;

import java.util.Locale;

public enum ReportType {
    ANNUAL("РІЧНИЙ"), MONTHLY("МІСЯЧНИЙ"), QUARTERLY("КВАРТАЛЬНИЙ");

    ReportType(String ukName) {
        this.ukName = ukName;
    }

    private String ukName;

    public String getLocaleName(Locale locale) {
        return (!locale.getLanguage().equals(Locale.US.getLanguage())) ?
                this.ukName : this.name();
    }
}