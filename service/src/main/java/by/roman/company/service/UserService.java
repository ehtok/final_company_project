package by.roman.company.service;

import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.UserDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String name);

    User saveUser(User user);

    List<CourseDTO> findAllCourseWithUser(User user);

    List<VacancyDTO>findAllVacancyWithUer(User user);

    UserDTO userInfo(Integer id);

    void removeUserToVacancy(Integer id);

    void removeUserToCourse(Integer id);
}
