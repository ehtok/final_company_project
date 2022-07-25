package by.roman.company.Service;

import by.roman.company.DTO.UserDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.User;
import by.roman.company.Entity.Vacancy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    Vacancy saveVacancy(VacancyDTO vacancy);

    VacancyDTO findVacancyById(Integer id);

    void deleteVacancy(Integer id);

    Page<VacancyDTO> findVacancyByValueWithSortAndPagination
            (String field, String direction, int pageNumber, int pageSize, String value);

    List<UserDTO> findAllUserInVacancy(VacancyDTO vacancy);


    void addUserToVacancy(Integer id);

    void removeUserToVacancy(Integer id);
}
