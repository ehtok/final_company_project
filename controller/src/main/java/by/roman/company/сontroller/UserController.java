package by.roman.company.сontroller;

import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.UserDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.User;
import by.roman.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import static by.roman.company.constants.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(USER_URL)
public class UserController {

    private final UserService userService;

    @GetMapping
    public String user(Model model) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute(USER_ATT, user);
        return TO_URL_USER;
    }

    @PostMapping
    public String saveUser(User user) {
        userService.saveUser(user);
        return REDIRECT_USER;
    }

    @GetMapping(UPDATE_GET_MAPPING)
    public String updateUser(Model model) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute(USER_ATT, user);
        return TO_URL_UPDATE_USER;
    }

    @GetMapping(COURSES_GET_MAPPING)
    public String listCoursesWithUser(Model model) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<CourseDTO> courses = userService.findAllCourseWithUser(user);
        model.addAttribute(COURSES_ATT, courses);
        model.addAttribute(USER_ATT, user);
        return TO_URL_USER_COURSES;
    }

    @GetMapping(VACANCIES_GET_MAPPING)
    public String listVacanciesWithUser(Model model) {
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<VacancyDTO> vacancies = userService.findAllVacancyWithUer(user);
        model.addAttribute(VACANCIES_ATT, vacancies);
        model.addAttribute(USER_ATT, user);
        return TO_URL_USER_VACANCIES;
    }

    @GetMapping(INFO_ID_GET_MAPPING)
    public String userInfo(@PathVariable(ID) Integer id, Model model) {
        UserDTO user = userService.userInfo(Optional.ofNullable(id).orElse(null));
        model.addAttribute(USER_ATT, user);
        return TO_URL_USER_INFO;
    }

    @GetMapping(COURSES_REMOVE_ID_GET_MAPPING)
    public String removeCourse(@PathVariable(ID) Integer id) {
        userService.removeUserToCourse(id);
        return REDIRECT_USER_COURSES;
    }

    @GetMapping(VACANCIES_REMOVE_ID_GET_MAPPING)
    public String removeVacancy(@PathVariable(ID) Integer id) {
        userService.removeUserToVacancy(id);
        return REDIRECT_USER_VACANCIES;
    }

}
