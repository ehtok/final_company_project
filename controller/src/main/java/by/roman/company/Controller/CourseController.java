package by.roman.company.Controller;

import by.roman.company.DTO.CompanyDTO;
import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.UserDTO;
import by.roman.company.Enum.LocationEnum;
import by.roman.company.Service.CompanyService;
import by.roman.company.Service.CourseService;
import by.roman.company.Service.UserService;
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
@RequestMapping(COURSE_URL)
public class CourseController {


    private final CourseService courseService;
    private final CompanyService companyService;
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
                                @RequestParam(value = COURSE_NAME_PARAM,
                                        required = false, defaultValue = DEFAULT_VALUE_NAME)
                                String name) {
        Page<CourseDTO> page = courseService.findAllCourse(field, sortDir, currentPage, size, name);
        int totalPages = page.getTotalPages();
        long totalElement = page.getTotalElements();
        List<CourseDTO> courseList = page.getContent();
        model.addAttribute(CURRENT_PAGE_ATT, currentPage);
        model.addAttribute(TOTAL_PAGE_ATT, totalPages);
        model.addAttribute(TOTAL_ELEMENT_ATT, totalElement);
        model.addAttribute(SORT_FIELD_PARAM, field);
        model.addAttribute(SORT_DIRECTION_PARAM, sortDir);
        model.addAttribute(COURSE_NAME_ATT, name);
        model.addAttribute(REVERSE_SORT_DIR_ATT, Sort.Direction.ASC.name().equals(sortDir) ? DESC : ASC);
        model.addAttribute(COURSES_ATT, courseList);
        model.addAttribute(USER_ATT, userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return TO_URL_COURSE;
    }

    @GetMapping(NEW_ID_GET_MAPPING)
    public String addVacancyToCompany(Model model, @PathVariable(value = ID) Integer id) {
        CompanyDTO company = companyService.findCompanyDTOById(id);
        model.addAttribute(COURSE_ATT, new CourseDTO());
        model.addAttribute(COMPANY_ID_ATT, company);
        model.addAttribute(LOCATION_ATT, LocationEnum.values());
        return TO_URL_NEW_COURSE;
    }

    @PostMapping
    public String saveVacancy(CourseDTO course) {
        courseService.saveCourse(course);
        return REDIRECT_COURSE;
    }

    @GetMapping(DELETE_ID_GET_MAPPING)
    public String deleteVacancy(@PathVariable(value = ID) Integer id) {
        String page;
        try {
            courseService.deleteCourse(id);
            page = REDIRECT_COURSE;
        } catch (Exception e) {
            e.printStackTrace();
            page = TO_URL_COURSE_ERROR;
        }
        return page;
    }

    @GetMapping(ADD_ID_GET_MAPPING)
    public String addVacancyToUser(@PathVariable(value = ID) Integer id) {
        courseService.addUserToCourse(id);
        return REDIRECT_COURSE;
    }

    @GetMapping(REMOVE_ID_GET_MAPPING)
    public String removeVacancyToUser(@PathVariable(value = "id") Integer id) {
        courseService.removeUserToCourse(id);
        return REDIRECT_COURSE;

    }

    @GetMapping(UPDATE_ID_GET_MAPPING)
    public String updateCourse(@PathVariable(value = ID) Integer id, Model model) {
        CourseDTO course = courseService.findCourseById(id);
        model.addAttribute(COURSE_ATT, course);
        model.addAttribute(LOCATION_ATT, LocationEnum.values());
        return TO_URL_UPDATE_COURSE;
    }


    @GetMapping(INFO_ID_GET_MAPPING)
    public String infoVacancy(@PathVariable(value = ID) Integer id, Model model) {
        model.addAttribute(COURSE_ATT, courseService.findCourseById(id));
        return TO_URL_INFO_COURSE;
    }

    @GetMapping(USERS_ID_GET_MAPPING)
    public String findAllUsers(@PathVariable(value = ID) Integer id, Model model) {
        List<UserDTO> users = courseService.findAllUserInCourse
                (courseService.findCourseById(Optional.ofNullable(id).orElse(null)));

        model.addAttribute(USERS_ATT, users);
        return "course_users";
    }

}
