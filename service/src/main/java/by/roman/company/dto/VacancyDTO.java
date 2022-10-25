package by.roman.company.dto;

import by.roman.company.entity.Company;
import by.roman.company.entity.Technology;
import by.roman.company.entity.User;
import by.roman.company.enumerate.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDTO {
    private Integer id;

    private String name;

    private WorkingTimeEnum workingTime;

    private String experience;

    private EnglishLevelEnum englishLevel;

    private ProfLevelEnum professionLevel;

    private String salary;

    private StatusEnum status;

    private LocationEnum location;

    private String technology;

    private String companyName;

    private Company company;

    private Set<Technology> technologies;
    private Set<User> user;
}
