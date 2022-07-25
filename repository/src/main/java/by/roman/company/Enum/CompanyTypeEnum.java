package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum CompanyTypeEnum {
    PRODUCT("Продуктовая"),
    STARTUP("Стартап"),
    OUTSOURCING("Аутсорсинг");

    private final String value;

    CompanyTypeEnum(String name) {
        this.value = name;
    }
}
