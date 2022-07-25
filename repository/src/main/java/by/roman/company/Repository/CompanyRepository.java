package by.roman.company.Repository;

import by.roman.company.Entity.Company;
import by.roman.company.Entity.Course;
import by.roman.company.Entity.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Page<Company> findByNameContaining(String name, Pageable pageable);

    @Query("from Vacancy where company.id like :id")
    List<Vacancy> findVacancyWithCompany(@Param("id") Integer id);

    @Query("from Course where company.id like :id")
    List<Course> findCourseWithCompany(@Param("id") Integer id);


}
