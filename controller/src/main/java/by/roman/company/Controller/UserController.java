package by.roman.company.Controller;

import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.UserDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.User;
import by.roman.company.Service.UserService;
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

import static by.roman.company.Constants.Constants.*;

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

}
