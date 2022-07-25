package by.roman.company.Controller;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Enum.CompanyTypeEnum;
import by.roman.company.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.roman.company.Constants.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(COMPANY_URL)
public class CompanyController {


    private final CompanyService companyService;

    @GetMapping
    public String findAllCompany(Model model,
                                 @RequestParam(value = PAGE_PARAM, required = false, defaultValue = ONE)
                                 int currentPage,
                                 @RequestParam(value = SORT_FIELD_PARAM,
                                         required = false, defaultValue = DEFAULT_VALUE_SORT_FIELD)
                                 String field,
                                 @RequestParam(value = SORT_DIRECTION_PARAM,
                                         required = false, defaultValue = DEFAULT_VALUE_SORT_DIRECTION)
                                 String sortDir,
                                 @RequestParam(value = SIZE_PARAM, required = false, defaultValue = DEFAULT_VALUE_SIZE)
                                 int size,
                                 @RequestParam(value = COMPANY_NAME_PARAM,
                                         required = false, defaultValue = DEFAULT_VALUE_COMPANY_NAME)
                                 String companyName) {
        Page<CompanyDTO> page = companyService.findCompanyByNamePaginationAndSort
                (companyName, field, sortDir, currentPage, size);
        int totalPages = page.getTotalPages();
        long totalElement = page.getTotalElements();
        List<CompanyDTO> companyList = page.getContent();
        model.addAttribute(CURRENT_PAGE_ATT, currentPage);
        model.addAttribute(TOTAL_PAGE_ATT, totalPages);
        model.addAttribute(TOTAL_ELEMENT_ATT, totalElement);
        model.addAttribute(SORT_FIELD_PARAM, field);
        model.addAttribute(COMPANY_NAME_PARAM, companyName);
        model.addAttribute(SORT_DIRECTION_PARAM, sortDir);
        model.addAttribute(REVERSE_SORT_DIR_ATT, Sort.Direction.ASC.name().equals(sortDir) ? DESC : ASC);
        model.addAttribute(COMPANIES_ATT, companyList);
        return COMPANY_TO_URL;
    }

    @GetMapping(value = NEW_URL)
    public String addCompany(Model model) {
        model.addAttribute(COMPANY_ATT, new CompanyDTO());
        model.addAttribute(COMPANY_TYPE_ATT, CompanyTypeEnum.values());
        return NEW_COMPANY_TO_URL;
    }

    @GetMapping(DELETE_ID_GET_MAPPING)
    public String deleteCompany(@PathVariable(value = ID) Integer id) {
        companyService.deleteCompany(id);
        return REDIRECT_COMPANY_URL;
    }

    @PostMapping
    public String saveCompany(CompanyDTO company) {
        companyService.saveCompany(company);
        return REDIRECT_COMPANY_URL;
    }

    @GetMapping(UPDATE_ID_GET_MAPPING)
    public String updateCompany(@PathVariable(value = ID) Integer id, Model model) {
        CompanyDTO company = companyService.findCompanyDTOById(id);
        model.addAttribute(COMPANY_ATT, company);
        model.addAttribute(COMPANY_TYPE_ATT, CompanyTypeEnum.values());
        return UPDATE_COMPANY_TO_URL;
    }

    @GetMapping(INFO_ID_GET_MAPPING)
    public String infoVacancy(@PathVariable(value = ID) Integer id, Model model) {
        model.addAttribute(COMPANY_ATT, companyService.findCompanyDTOById(id));
        return INFO_COMPANY_TO_URL;
    }

    @GetMapping(VACANCIES_ID_GET_MAPPING)
    public String vacanciesInfo(@PathVariable(value = ID) Integer id, Model model) {
        List<VacancyDTO> vacancies = companyService.findVacancyInCompany(id);
        model.addAttribute(VACANCIES_ATT, vacancies);
        return COMPANY_VACANCIES_TO_URL;
    }

    @GetMapping(COURSES_ID_GET_MAPPING)
    public String coursesInfo(@PathVariable(value = ID) Integer id, Model model) {
        List<CourseDTO> courses = companyService.findCoursesInCompany(id);
        model.addAttribute(COURSES_ATT, courses);
        return COMPANY_COURSES_TO_URL;
    }
}
