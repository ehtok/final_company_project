package by.roman.company.сontroller;

import by.roman.company.dto.CompanyDTO;
import by.roman.company.dto.UserDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.enumerate.*;
import by.roman.company.service.CompanyService;
import by.roman.company.service.TechnologyService;
import by.roman.company.service.UserService;
import by.roman.company.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static by.roman.company.constants.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(VACANCY_URL)
public class VacancyController {

    private final VacancyService vacancyService;
    private final CompanyService companyService;
    private final TechnologyService technologyService;
    private final UserService userService;


    @GetMapping
    public String findAllCourse(Model model,
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
                                @RequestParam(value = VALUE_PARAM, required = false, defaultValue = DEFAULT_VALUE_PARAM)
                                String value) {
        Page<VacancyDTO> page =
                vacancyService.findVacancyByValueWithSortAndPagination(field, sortDir, currentPage, size, value);
        int totalPages = page.getTotalPages();
        long totalElement = page.getTotalElements();
        List<VacancyDTO> vacancyList = page.getContent();
        model.addAttribute(TOTAL_ELEMENT_ATT, totalElement);
        model.addAttribute(CURRENT_PAGE_ATT, currentPage);
        model.addAttribute(TOTAL_PAGE_ATT, totalPages);
        model.addAttribute(SORT_FIELD_PARAM, field);
        model.addAttribute(ACTIVE_ATT, StatusEnum.ACTIVE);
        model.addAttribute("profLevel", ProfLevelEnum.values());
        model.addAttribute(VALUE_PARAM, value);
        model.addAttribute(SORT_DIRECTION_PARAM, sortDir);
        model.addAttribute(REVERSE_SORT_DIR_ATT, Sort.Direction.ASC.name().equals(sortDir) ? DESC : ASC);
        model.addAttribute(VACANCIES_ATT, vacancyList);
        model.addAttribute(USER_ATT,
                userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return TO_URL_VACANCY;
    }

    @GetMapping(NEW_ID_GET_MAPPING)
    public String addVacancy(Model model, @PathVariable(value = ID) Integer id) {
        CompanyDTO company = companyService.findCompanyDTOById(Optional.ofNullable(id).orElse(null));
        model.addAttribute(VACANCY_ATT, new VacancyDTO());
        model.addAttribute(COMPANY_ID_ATT, company);
        model.addAttribute(LOCATION_ATT, LocationEnum.values());
        model.addAttribute(STATUS_ATT, StatusEnum.values());
        model.addAttribute(WORKING_TIME_ATT, WorkingTimeEnum.values());
        model.addAttribute(PROFESSION_LEVEL_ATT, ProfLevelEnum.values());
        model.addAttribute(ENGLISH_LEVEL_ATT, EnglishLevelEnum.values());
        model.addAttribute(TECH_ATT, technologyService.findAllTechnologies());
        return TO_URL_NEW_VACANCY;
    }

    @PostMapping
    public String saveVacancy(VacancyDTO vacancy) {
        vacancyService.saveVacancy(vacancy);
        return REDIRECT_VACANCY;
    }


    @GetMapping(DELETE_ID_GET_MAPPING)
    public String deleteVacancy(@PathVariable(value = ID) Integer id) {
        String page;
        try {
            vacancyService.deleteVacancy(Optional.ofNullable(id).orElse(null));
            page = REDIRECT_VACANCY;
        } catch (Exception e) {
            page = "vacancyError";
        }
        return page;
    }


    @GetMapping(UPDATE_ID_GET_MAPPING)
    public String updateVacancy(@PathVariable(value = ID) Integer id, Model model) {
        VacancyDTO vacancy = vacancyService.findVacancyById(Optional.ofNullable(id).orElse(null));
        model.addAttribute(VACANCY_ATT, vacancy);
        model.addAttribute(TECH_ATT, technologyService.findAllTechnologies());
        model.addAttribute(STATUS_ATT, StatusEnum.values());
        model.addAttribute(WORKING_TIME_ATT, WorkingTimeEnum.values());
        model.addAttribute(LOCATION_ATT, LocationEnum.values());
        model.addAttribute(PROFESSION_LEVEL_ATT, ProfLevelEnum.values());
        model.addAttribute(ENGLISH_LEVEL_ATT, EnglishLevelEnum.values());
        return TO_URL_UPDATE_VACANCY;
    }

    @GetMapping(INFO_ID_GET_MAPPING)
    public String infoVacancy(@PathVariable(value = ID) Integer id, Model model) {
        VacancyDTO vacancy = vacancyService.findVacancyById(Optional.ofNullable(id).orElse(null));
        model.addAttribute(VACANCY_ATT, vacancy);
        return TO_URL_INFO_VACANCY;
    }

    @GetMapping(ADD_ID_GET_MAPPING)
    public String addVacancyToUser(@PathVariable(value = ID) Integer id) {
        vacancyService.addUserToVacancy(Optional.ofNullable(id).orElse(null));
        return REDIRECT_VACANCY;

    }

    @GetMapping(REMOVE_ID_GET_MAPPING)
    public String removeVacancyToUser(@PathVariable(value = ID) Integer id) {
        vacancyService.removeUserToVacancy(Optional.ofNullable(id).orElse(null));
        return REDIRECT_VACANCY;
    }


    @GetMapping(USERS_ID_GET_MAPPING)
    public String findAllUsers(@PathVariable(value = ID) Integer id, Model model) {
        List<UserDTO> users = vacancyService.findAllUserInVacancy
                (vacancyService.findVacancyById(Optional.ofNullable(id).orElse(null)));
        model.addAttribute(USERS_ATT, users);
        return TO_URL_VACANCY_USERS;
    }
}
