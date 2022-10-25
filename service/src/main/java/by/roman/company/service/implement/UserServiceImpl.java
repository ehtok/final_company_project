package by.roman.company.service.implement;

import by.roman.company.converter.Converter;
import by.roman.company.converter.implement.CourseConverterImpl;
import by.roman.company.converter.implement.UserConverterImpl;
import by.roman.company.converter.implement.VacancyConverterImpl;
import by.roman.company.dto.CourseDTO;
import by.roman.company.dto.UserDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.Course;
import by.roman.company.entity.User;
import by.roman.company.entity.Vacancy;
import by.roman.company.repository.CourseRepository;
import by.roman.company.repository.UserRepository;
import by.roman.company.repository.VacancyRepository;
import by.roman.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final VacancyRepository vacancyRepository;
    private Converter<Course, CourseDTO> courseConverter = new CourseConverterImpl();
    private Converter<Vacancy, VacancyDTO> vacancyConverter = new VacancyConverterImpl();
    private Converter<User, UserDTO> userConverter = new UserConverterImpl();


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<CourseDTO> findAllCourseWithUser(User user) {
        return courseConverter.toListDTO(courseRepository.findByUsers(user));
    }

    @Override
    public List<VacancyDTO> findAllVacancyWithUer(User user) {
        return vacancyConverter.toListDTO(vacancyRepository.findByUsers(user));
    }

    @Override
    public UserDTO userInfo(Integer id) {
        return userConverter.toDTO(userRepository.findById(id).orElse(null));
    }

    @Override
    public void removeUserToVacancy(Integer id) {
        VacancyDTO vacancy = vacancyConverter.toDTO(vacancyRepository.findById(id).orElse(null));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getVacancies().remove(vacancyConverter.toEntity(vacancy));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserToCourse(Integer id) {
        CourseDTO course = courseConverter.toDTO(courseRepository.findById(id).orElse(null));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getCourses().remove(courseConverter.toEntity(course));
        userRepository.save(user);
    }


}
