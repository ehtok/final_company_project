package by.roman.company.Service.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.Converter.implement.UserConverterImpl;
import by.roman.company.Converter.implement.VacancyConverterImpl;
import by.roman.company.DTO.UserDTO;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.User;
import by.roman.company.Entity.Vacancy;
import by.roman.company.Repository.UserRepository;
import by.roman.company.Repository.VacancyRepository;
import by.roman.company.Service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.roman.company.Service.Constant.ONE;


@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {


    private final VacancyRepository vacancyRepository;

    private final UserRepository userRepository;
    private Converter<Vacancy, VacancyDTO> vacancyConverter = new VacancyConverterImpl();
    private Converter userConverter = new UserConverterImpl();

    @Override
    @Transactional
    public Vacancy saveVacancy(VacancyDTO vacancy) {
        Vacancy entity = vacancyConverter.toEntity(vacancy);
        return vacancyRepository.save(entity);
    }

    @Override
    public VacancyDTO findVacancyById(Integer id) {
        return vacancyConverter.toDTO(vacancyRepository.findById(id).orElse(null));
    }


    @Override
    public void deleteVacancy(Integer id) {
        vacancyRepository.deleteById(id);
    }

    @Override
    public Page<VacancyDTO> findVacancyByValueWithSortAndPagination
            (String field, String direction, int pageNumber, int pageSize, String value) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Page<Vacancy> vacancies = vacancyRepository.findVacanciesByNameAndProfessionLevel
                (value, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return vacancies.map(vacancyConverter::toDTO);
    }

    @Override
    public List<UserDTO> findAllUserInVacancy(VacancyDTO vacancyDTO) {
        return userConverter.toListDTO(userRepository.findByVacancies(vacancyConverter.toEntity(vacancyDTO)));
    }


    @Override
    @Transactional
    public void addUserToVacancy(Integer id) {
        VacancyDTO vacancy = vacancyConverter.toDTO(vacancyRepository.findById(id).orElse(null));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getVacancies().add(vacancyConverter.toEntity(vacancy));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUserToVacancy(Integer id) {
        VacancyDTO vacancy = vacancyConverter.toDTO(vacancyRepository.findById(id).orElse(null));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getVacancies().remove(vacancyConverter.toEntity(vacancy));
        userRepository.save(user);
    }
}
