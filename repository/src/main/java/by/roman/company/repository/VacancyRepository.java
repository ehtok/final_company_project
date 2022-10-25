package by.roman.company.repository;

import by.roman.company.entity.User;
import by.roman.company.entity.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer>, JpaSpecificationExecutor<Vacancy> {

    @Query("select vacancy from Vacancy vacancy where " +
            "concat(vacancy.name,vacancy.location) like %?1%")
    Page<Vacancy> findVacanciesByName(String value, Pageable pageable);

    List<Vacancy> findByUsers(User user);


}
