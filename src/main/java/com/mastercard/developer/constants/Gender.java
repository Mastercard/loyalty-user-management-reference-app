package com.mastercard.developer.constants;

/**
 * These are actual allowable ISO codes of Gender
 */

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
