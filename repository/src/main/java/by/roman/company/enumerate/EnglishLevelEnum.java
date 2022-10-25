package by.roman.company.enumerate;

import lombok.Getter;

@Getter
public enum EnglishLevelEnum {
    A1("A1"),
    A2("A2"),
    B1("B1"),
    B2("B2"),
    C1("C1"),
    C2("C2");

    private final String value;

    EnglishLevelEnum(String name) {
        this.value = name;
    }
}
