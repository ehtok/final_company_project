package by.roman.company.service;

import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.UserDTO;
import by.roman.company.entity.Course;
import org.springframework.data.domain.Page;

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
