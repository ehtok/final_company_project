package by.roman.company.service.implement;

import by.roman.company.converter.Converter;
import by.roman.company.converter.implement.CourseConverterImpl;
import by.roman.company.converter.implement.UserConverterImpl;
import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.UserDTO;
import by.roman.company.entity.Course;
import by.roman.company.entity.User;
import by.roman.company.repository.CourseRepository;
import by.roman.company.repository.UserRepository;
import by.roman.company.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.roman.company.service.Constant.ONE;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;
    private final Converter<Course, CourseDTO> converter = new CourseConverterImpl();

    private final Converter<User, UserDTO> userConverter = new UserConverterImpl();

    @Override
    public List<CourseDTO> findAllCourses() {
        return converter.toListDTO(courseRepository.findAll());
    }

    @Override
    public Course saveCourse(CourseDTO course) {
        return courseRepository.save(converter.toEntity(course));
    }

    @Override
    public CourseDTO findCourseById(Integer id) {
        return converter.toDTO(courseRepository.findById(id).orElse(null));
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Page<CourseDTO> findAllCourse
            (String field, String direction, int pageNumber,
             int pageSize, String name) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Course> courses = courseRepository.findCoursesByName(name, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return courses.map(converter::toDTO);
    }

    @Override
    @Transactional
    public void addUserToCourse(Integer id) {
        CourseDTO course = converter.toDTO(courseRepository.findById(id).orElse(null));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getCourses().add(converter.toEntity(course));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserToCourse(Integer id) {
        CourseDTO course = converter.toDTO(courseRepository.findById(id).orElse(null));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getCourses().remove(converter.toEntity(course));
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> findAllUserInCourse(CourseDTO course) {
        return userConverter.toListDTO(userRepository.findByCourses(converter.toEntity(course)));
    }

}
