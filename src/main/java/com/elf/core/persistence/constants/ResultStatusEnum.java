package com.elf.core.persistence.constants;

/**
 * @Description: 返回值枚举
 * @Author: Liyiming
 * @Date: 2018/5/20
 */
public enum ResultStatusEnum {
    SUCCESS(0), ERROR(-1), FAILED(1);

    private int value;

    ResultStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args){
        System.out.println(ResultStatusEnum.SUCCESS);
        System.out.println(ResultStatusEnum.ERROR.getValue());
    }
}
