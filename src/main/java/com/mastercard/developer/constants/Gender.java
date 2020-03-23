package com.mastercard.developer.constants;

public enum Gender {

    NOT_KNOWN("0"),
    MALE("1"),
    FEMALE("2"),
    NOT_APPLICABLE("9");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
