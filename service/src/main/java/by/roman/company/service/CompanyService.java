package by.roman.company.service;

import by.roman.company.dto.CompanyDTO;
import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company saveCompany(CompanyDTO company);

    void deleteCompany(Integer id);

    CompanyDTO findCompanyDTOById(Integer id);

   List<CompanyDTO>  findAll();

    Page<CompanyDTO> findCompanyByNamePaginationAndSort
            (String name, String field, String direction, int pageNumber, int pageSize);

    List<VacancyDTO> findVacancyInCompany(Integer id);

    List<CourseDTO> findCoursesInCompany(Integer id);
}
