package by.roman.company.Controller;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.DTO.UserDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Enum.*;
import by.roman.company.Service.CompanyService;
import by.roman.company.Service.TechnologyService;
import by.roman.company.Service.UserService;
import by.roman.company.Service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static by.roman.company.Constants.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(VacancyController.VACANCY_URL)
public class VacancyController {
    public static final String VACANCY_URL = "/vacancy";
    public static final String VALUE_PARAM = "value";
    public static final String DEFAULT_VALUE_PARAM = "";
    public static final String TO_URL_VACANCY = "vacancy";
    public static final String VACANCY_ATT = "vacancy";
    public static final String STATUS_ATT = "status";
    public static final String WORKING_TIME_ATT = "workingTime";
    public static final String PROFESSION_LEVEL_ATT = "professionLevel";
    public static final String ENGLISH_LEVEL_ATT = "englishLevel";
    public static final String TECH_ATT = "tech";
    public static final String TO_URL_NEW_VACANCY = "new_vacancy";
    public static final String REDIRECT_VACANCY = "redirect:/vacancy";
    public static final String TO_URL_UPDATE_VACANCY = "update_vacancy";
    public static final String TO_URL_INFO_VACANCY = "info_vacancy";
    public static final String USERS_ID_GET_MAPPING = "/users/{id}";
    public static final String USERS_ATT = "users";
    public static final String TO_URL_VACANCY_USERS = "vacancy_users";
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
        vacancyService.deleteVacancy(Optional.ofNullable(id).orElse(null));
        return REDIRECT_VACANCY;
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
