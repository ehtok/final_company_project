package by.roman.company.service.implement;

import by.roman.company.converter.Converter;
import by.roman.company.converter.implement.CompanyConverterImpl;
import by.roman.company.converter.implement.CourseConverterImpl;
import by.roman.company.converter.implement.VacancyConverterImpl;
import by.roman.company.dto.CompanyDTO;
import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.Company;
import by.roman.company.repository.CompanyRepository;
import by.roman.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.roman.company.service.Constant.ONE;

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
    public List<CompanyDTO> findAll() {
        return companyConverter.toListDTO(companyRepository.findAll());
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
