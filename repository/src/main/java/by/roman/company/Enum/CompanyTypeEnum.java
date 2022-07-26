package by.roman.company.Enum;

import lombok.Getter;

@Getter
public enum CompanyTypeEnum {
    PRODUCT("Продуктовая"),
    STARTUP("Стартап"),
    OUTSOURCING("Аутсорсинг"),
    GAME("Разработка игр"),
    MOBILE("Мобильная разработка"),
    IT("ИТ компания");

    private final String value;

    CompanyTypeEnum(String name) {
        this.value = name;
    }
}
