package by.roman.company.Service.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.Converter.implement.CourseConverterImpl;
import by.roman.company.Converter.implement.UserConverterImpl;
import by.roman.company.Converter.implement.VacancyConverterImpl;
import by.roman.company.DTO.CourseDTO;
import by.roman.company.DTO.UserDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.User;
import by.roman.company.Repository.CourseRepository;
import by.roman.company.Repository.UserRepository;
import by.roman.company.Repository.VacancyRepository;
import by.roman.company.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final VacancyRepository vacancyRepository;
    private Converter courseConverter = new CourseConverterImpl();
    private Converter vacancyConverter = new VacancyConverterImpl();
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


}
