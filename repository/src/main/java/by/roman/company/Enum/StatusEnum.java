package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum StatusEnum {
    ACTIVE("Открыта"),
    OFF("Закрыта");

    private final String value;

    StatusEnum(String name) {
        this.value = name;
    }
}
