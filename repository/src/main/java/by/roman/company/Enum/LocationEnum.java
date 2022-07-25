package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum LocationEnum {
    MINSK("Минск"),
    GRODNO("Гродно"),
    VITEBSK("Витебск"),
    MOGILEV("Могилев"),
    BREST("Брест"),
    GOMEL("Гомель");

    private final String value;

    LocationEnum(String name) {
        this.value = name;
    }
}
