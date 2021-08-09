package com.example.taxservice.entity.enums;

import java.util.Locale;

public enum Currency {
    UAH("ГРН"), USD("Долар США"),
    EUR("ЄВРО"), GBP ("Фунт Стерліргів");

    Currency(String ukName) {
        this.ukName = ukName;
    }

    private String ukName;

    public String getLocaleName(Locale locale) {
        return (!locale.getLanguage().equals(Locale.US.getLanguage())) ?
                this.ukName : this.name();
    }
}
