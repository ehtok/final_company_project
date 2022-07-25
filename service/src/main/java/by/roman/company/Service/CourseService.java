package by.roman.company.Service;

import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.UserDTO;
import by.roman.company.Entity.Course;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    List<CourseDTO> findAllCourses();

    Course saveCourse(CourseDTO course);

    CourseDTO findCourseById(Integer id);

    void deleteCourse(Integer id);

    Page<CourseDTO> findAllCourse(String field, String direction, int pageNumber, int pageSize, String name);

    void addUserToCourse(Integer id);

    void removeUserToCourse(Integer id);

    List<UserDTO> findAllUserInCourse(CourseDTO course);

}
