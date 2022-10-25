package by.roman.company.enumerate;

import lombok.Getter;

@Getter
public enum WorkingTimeEnum {
    FULL("Полный день"),
    PART("Частичная занятость"),
    REMOTE("Удаленная работа");

    private final String value;

    WorkingTimeEnum(String name) {
        this.value = name;
    }
}
