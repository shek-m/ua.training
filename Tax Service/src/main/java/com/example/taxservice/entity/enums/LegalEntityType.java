package com.example.taxservice.entity.enums;

import java.util.Locale;

public enum LegalEntityType {

    DAT("ДАТ"), FOP("ФОП"),
    TOV("ТОВ"), PP("ПП"),
    VAT("ВАТ"), ZAT("ЗАТ"),
    KT("КТ");

    LegalEntityType(String ukName) {
        this.ukName = ukName;
    }

    private String ukName;

    public String getLocaleName(Locale locale) {
        return (!locale.getLanguage().equals(Locale.US.getLanguage())) ?
                this.ukName : this.name();
    }
}