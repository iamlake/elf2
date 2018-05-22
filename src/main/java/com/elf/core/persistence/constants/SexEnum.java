package com.elf.core.persistence.constants;

/**
 * @program: elf
 * @description: SexEnum
 * @author: Liyiming
 * @create: 2018-05-20 12:14
 **/
public enum SexEnum {
    MALE("1"), FEMALE("2"), UNKNOWN("3");

    private String value;

    SexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
