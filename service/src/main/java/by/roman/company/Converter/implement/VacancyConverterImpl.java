package by.roman.company.Converter.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.DTO.VacancyDTO;
import by.roman.company.Entity.Technology;
import by.roman.company.Entity.Vacancy;

import java.util.List;
import java.util.stream.Collectors;

public class VacancyConverterImpl implements Converter<Vacancy, VacancyDTO> {

    @Override
    public VacancyDTO toDTO(Vacancy vacancy) {
        VacancyDTO vacancyDTO = VacancyDTO.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .workingTime(vacancy.getWorkingTime())
                .experience(vacancy.getExperience())
                .englishLevel(vacancy.getEnglishLevel())
                .professionLevel(vacancy.getProfessionLevel())
                .salary(vacancy.getSalary().toString())
                .status(vacancy.getStatus())
                .companyName(vacancy.getCompany() == null ? null : vacancy.getCompany().getName())
                .technology(vacancy.getTechnologies().stream().map(Technology::getName).collect(Collectors.joining(",")))
                .location(vacancy.getLocation())
                .technologies(vacancy.getTechnologies())
                .user(vacancy.getUsers())
                .build();
        return vacancyDTO;
    }

    @Override
    public List<VacancyDTO> toListDTO(List<Vacancy> vacancies) {
        return vacancies.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Vacancy toEntity(VacancyDTO vacancyDTO) {
        Vacancy vacancy = Vacancy.builder()
                .id(vacancyDTO.getId())
                .name(vacancyDTO.getName())
                .workingTime(vacancyDTO.getWorkingTime())
                .experience(vacancyDTO.getExperience())
                .englishLevel(vacancyDTO.getEnglishLevel())
                .professionLevel(vacancyDTO.getProfessionLevel())
                .salary(Integer.valueOf(vacancyDTO.getSalary()))
                .status(vacancyDTO.getStatus())
                .location(vacancyDTO.getLocation())
                .company(vacancyDTO.getCompany())
                .technologies(vacancyDTO.getTechnologies())
                .users(vacancyDTO.getUser())
                .build();
        return vacancy;
    }
}
