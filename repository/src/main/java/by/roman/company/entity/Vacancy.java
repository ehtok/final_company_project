package by.roman.company.entity;

import by.roman.company.enumerate.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "vacancy")
@Entity
public class Vacancy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vacancy_name")
    private String name;

    @Column(name = "working_time")
    @Enumerated(EnumType.STRING)
    private WorkingTimeEnum workingTime;

    @Column(name = "experience")
    private String experience;

    @Column(name = "vacancy_location")
    @Enumerated(value = EnumType.STRING)
    private LocationEnum location;


    @Column(name = "english_level")
    @Enumerated(EnumType.STRING)
    private EnglishLevelEnum englishLevel;

    @Column(name = "profession_level")
    @Enumerated(EnumType.STRING)
    private ProfLevelEnum professionLevel;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "company_id", updatable = false)
    private Company company;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "technology_vacancy",
            joinColumns = {@JoinColumn(name = "id_vacancy")},
            inverseJoinColumns = {@JoinColumn(name = "id_technology")})
    private Set<Technology> technologies = new HashSet<>();


    @ManyToMany(mappedBy = "vacancies", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();


    @Override
    public String toString() {
        return "Vacancy " +
                "id = " + id +
                ", name = " + name +
                ", workingTime = " + workingTime +
                ", experience = " + experience +
                ", location = " + location +
                ", englishLevel = " + englishLevel +
                ", professionLevel = " + professionLevel +
                ", salary = " + salary +
                ", status = " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id.equals(vacancy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
