package by.roman.company.Repository;

import by.roman.company.Entity.Course;
import by.roman.company.Entity.User;
import by.roman.company.Entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findByVacancies(Vacancy vacancy);

    List<User> findByCourses(Course course);
}
