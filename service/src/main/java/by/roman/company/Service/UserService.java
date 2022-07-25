package by.roman.company.Service;

import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.UserDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String name);

    User saveUser(User user);

    List<CourseDTO> findAllCourseWithUser(User user);

    List<VacancyDTO>findAllVacancyWithUer(User user);

    UserDTO userInfo(Integer id);


}
