package by.roman.company.service;

import by.roman.company.dto.UserDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.Vacancy;
import by.roman.company.specification.filter.VacancyFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    Vacancy saveVacancy(VacancyDTO vacancy);

    VacancyDTO findVacancyById(Integer id);

    void deleteVacancy(Integer id);

    Page<VacancyDTO> findVacancyByValueWithSortAndPagination
            (String field, String direction, int pageNumber, int pageSize, String value);

    List<UserDTO> findAllUserInVacancy(VacancyDTO vacancy);

    Page<VacancyDTO> findVacanciesWithSpecification(String field, String direction, int pageNumber, int pageSize, VacancyFilter filter);


    void addUserToVacancy(Integer id);

    void removeUserToVacancy(Integer id);
}
