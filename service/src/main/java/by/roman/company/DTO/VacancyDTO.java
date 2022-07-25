package by.roman.company.DTO;

import by.roman.company.Entity.Company;
import by.roman.company.Entity.Technology;
import by.roman.company.Entity.User;
import by.roman.company.Enum.*;
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
