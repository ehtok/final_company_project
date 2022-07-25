package by.roman.company.DTO;


import by.roman.company.Enum.CompanyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Integer id;

    private String name;

    private Integer companySize;

    private CompanyTypeEnum companyType;

    private String site;

    private String mail;

    private String description;


}
