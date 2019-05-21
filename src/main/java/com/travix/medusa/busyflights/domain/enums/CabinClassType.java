package com.travix.medusa.busyflights.domain.enums;

public enum  CabinClassType {
    ECONOMY("E"),
    BUSINESS("B");

    private String value;

    CabinClassType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
