package by.roman.company.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyFilter {

    private String nameFilter;

    private String experienceFilter;
}
