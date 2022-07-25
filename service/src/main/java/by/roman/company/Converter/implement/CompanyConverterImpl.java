package by.roman.company.Converter.implement;

import by.roman.company.Converter.Converter;
import by.roman.company.DTO.CompanyDTO;
import by.roman.company.Entity.Company;
import by.roman.company.Entity.Course;
import by.roman.company.Entity.Vacancy;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyConverterImpl implements Converter<Company, CompanyDTO> {

    @Override
    public CompanyDTO toDTO(Company company) {
        CompanyDTO companyDTO = CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .description(company.getDescription())
                .site(company.getSite())
                .mail(company.getMail())
                .build();
        return companyDTO;
    }


    @Override
    public List<CompanyDTO> toListDTO(List<Company> companies) {
        return companies.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Company toEntity(CompanyDTO companyDTO) {
        Company company = Company.builder()
                .id(companyDTO.getId())
                .name(companyDTO.getName())
                .companySize(companyDTO.getCompanySize())
                .companyType(companyDTO.getCompanyType())
                .description(companyDTO.getDescription())
                .site(companyDTO.getSite())
                .mail(companyDTO.getMail())
                .build();
        return company;
    }
}
