package by.roman.company.enumerate;

import lombok.Getter;

@Getter
public enum ProfLevelEnum {
    INTERN("Intern"),
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior"),
    LEAD("Team Lead");

    private final String value;

    ProfLevelEnum(String name) {
        this.value = name;
    }

}
