package by.roman.company.repository;

import by.roman.company.entity.Course;
import by.roman.company.entity.User;
import by.roman.company.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findByVacancies(Vacancy vacancy);

    List<User> findByCourses(Course course);
}
