package by.roman.company.dto;

import by.roman.company.entity.Company;
import by.roman.company.entity.User;
import by.roman.company.enumerate.LocationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;

    private String name;

    private String dateStart;

    private String dateFinish;

    private String description;

    private LocationEnum location;

    private String companyName;

    private Company company;

    private Set<User> user;


}
