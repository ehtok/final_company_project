package by.roman.company.service.implement;

import by.roman.company.converter.Converter;
import by.roman.company.converter.implement.UserConverterImpl;
import by.roman.company.converter.implement.VacancyConverterImpl;
import by.roman.company.dto.UserDTO;
import by.roman.company.dto.VacancyDTO;
import by.roman.company.entity.User;
import by.roman.company.entity.Vacancy;
import by.roman.company.repository.UserRepository;
import by.roman.company.repository.VacancyRepository;
import by.roman.company.service.VacancyService;
import by.roman.company.specification.VacancySpecification;
import by.roman.company.specification.filter.VacancyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.roman.company.service.Constant.ONE;


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
        Page<Vacancy> vacancies = vacancyRepository.findVacanciesByName
                (value, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return vacancies.map(vacancyConverter::toDTO);
    }

    @Override
    public List<UserDTO> findAllUserInVacancy(VacancyDTO vacancyDTO) {
        return userConverter.toListDTO(userRepository.findByVacancies(vacancyConverter.toEntity(vacancyDTO)));
    }

    @Override
    public Page<VacancyDTO> findVacanciesWithSpecification(String field, String direction, int pageNumber, int pageSize, VacancyFilter filter) {
        Sort sort = Sort.Direction.ASC.name().equalsIgnoreCase(direction) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Specification<Vacancy> vacancySpecification = Specification
                .where(
                        Optional.ofNullable(filter.getNameFilter())
                                .map(VacancySpecification::getVacancyByNameSpecification)
                                .orElse(null))
                .and(
                        Optional.ofNullable(filter.getExperienceFilter())
                                .map(VacancySpecification::getVacancyByExperienceSpecification)
                                .orElse(null));
        Page<Vacancy> vacancies = vacancyRepository.findAll(vacancySpecification, PageRequest.of(pageNumber - ONE, pageSize, sort));
        return vacancies.map(vacancyConverter::toDTO);
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
