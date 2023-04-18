package com.example.demo.entity.enums;

public enum CurrencyEnum {
    USD("&#36"),
    KZT("&#8376"),
    UAH("&#8372"),
    SOM("SUM");

    private final String iconCode;

    CurrencyEnum(String iconCode) {
        this.iconCode = iconCode;
    }

    public String getIconCode() {
        return iconCode;
    }
}
