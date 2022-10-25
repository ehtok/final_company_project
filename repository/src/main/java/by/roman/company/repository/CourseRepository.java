package by.roman.company.repository;

import by.roman.company.entity.Course;
import by.roman.company.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("select course from Course course where " + "course.name like %?1%")
    Page<Course> findCoursesByName(String name, Pageable pageable);

    List<Course> findByUsers(User user);
}
