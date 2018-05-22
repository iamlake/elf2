package com.elf.core.persistence.constants;

/**
 * @program: elf
 * @description: WeekdayEnum
 * @author: Liyiming
 * @create: 2018-05-20 12:05
 **/
public enum WeekdayEnum {
    SUN(0), MON(1), TUS(2), WED(3), THU(4), FRI(5), SAT(6);

    private int value;

    WeekdayEnum(int value) {
        this.value = value;
    }

    public static WeekdayEnum getNextDay(WeekdayEnum nowDay) {
        int nextDayValue = nowDay.value;
        int weekdaySize = 7;
        if (++nextDayValue == weekdaySize) {
            nextDayValue = 0;
        }
        return getWeekdayByValue(nextDayValue);
    }

    public static WeekdayEnum getWeekdayByValue(int value) {
        for (WeekdayEnum c : WeekdayEnum.values()) {
            if (c.value == value) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("nowday ====> " + WeekdayEnum.SAT);
        System.out.println("nowday int ====> " + WeekdayEnum.SAT.ordinal());
        System.out.println("nextday ====> " + WeekdayEnum.getNextDay(WeekdayEnum.SAT));
    }
}
