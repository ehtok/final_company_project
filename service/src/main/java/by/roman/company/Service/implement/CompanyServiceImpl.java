package by.roman.company.Service.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.Converter.implement.CompanyConverterImpl;
import by.roman.company.Converter.implement.CourseConverterImpl;
import by.roman.company.Converter.implement.VacancyConverterImpl;
import by.roman.company.DTO.CompanyDTO;
import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Repository.CompanyRepository;
import by.roman.company.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.roman.company.Service.Constant.ONE;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    private final Converter<Company, CompanyDTO> companyConverter = new CompanyConverterImpl();
    private final Converter vacancyConverter = new VacancyConverterImpl();
    private final Converter courseConverter = new CourseConverterImpl();

    @Override
    public Company saveCompany(CompanyDTO company) {
        return companyRepository.save(companyConverter.toEntity(company));
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDTO findCompanyDTOById(Integer id) {
        return companyConverter.toDTO(companyRepository.findById(id).orElse(null));
    }

    @Override
    public Page<CompanyDTO> findCompanyByNamePaginationAndSort
            (String name, String field, String direction, int pageNumber, int pageSize) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Company> companies = companyRepository.findByNameContaining
                (name, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return companies.map(companyConverter::toDTO);
    }


    @Override
    public List<VacancyDTO> findVacancyInCompany(Integer id) {
        return vacancyConverter.toListDTO(companyRepository.findVacancyWithCompany(id));
    }

    @Override
    public List<CourseDTO> findCoursesInCompany(Integer id) {
        return courseConverter.toListDTO(companyRepository.findCourseWithCompany(id));
    }


}
