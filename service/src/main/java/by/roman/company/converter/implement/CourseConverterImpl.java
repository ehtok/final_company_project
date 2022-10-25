package by.roman.company.converter.implement;

import by.roman.company.converter.Converter;
import by.roman.company.dto.CourseDTO;
import by.roman.company.entity.Course;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CourseConverterImpl implements Converter<Course, CourseDTO> {

    @Override
    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .dateStart(String.valueOf(course.getDateStart()))
                .dateFinish(String.valueOf(course.getDateFinish()))
                .description(course.getDescription())
                .location(course.getLocation())
                .companyName(course.getCompany() == null ? null : course.getCompany().getName())
                .user(course.getUsers())
                .build();
        return courseDTO;
    }

    @Override
    public List<CourseDTO> toListDTO(List<Course> courses) {
        return courses.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Course toEntity(CourseDTO courseDTO) {
        Course course = Course.builder()
                .id(courseDTO.getId())
                .name(courseDTO.getName())
                .dateStart(LocalDate.parse(courseDTO.getDateStart()))
                .dateFinish(LocalDate.parse(courseDTO.getDateFinish()))
                .description(courseDTO.getDescription())
                .location(courseDTO.getLocation())
                .company(courseDTO.getCompany())
                .users(courseDTO.getUser())
                .build();
        return course;
    }
}
