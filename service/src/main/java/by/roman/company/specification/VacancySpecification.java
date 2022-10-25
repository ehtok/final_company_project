package by.roman.company.specification;

import by.roman.company.entity.Vacancy;
import by.roman.company.entity.Vacancy_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class VacancySpecification {

    public static Specification<Vacancy> getVacancyByNameSpecification(String name) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.equal(root.get(Vacancy_.NAME), name));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Vacancy> getVacancyByExperienceSpecification(String experience) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (experience != null) {
                predicates.add(criteriaBuilder.equal(root.get(Vacancy_.EXPERIENCE), experience));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
