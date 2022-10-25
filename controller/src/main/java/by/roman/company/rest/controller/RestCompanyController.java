package by.roman.company.rest.controller;

import by.roman.company.dto.CompanyDTO;
import by.roman.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company-rest")
@RequiredArgsConstructor
public class RestCompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDTO> getCompanies() {
        return companyService.findAll();
    }
}
