package by.roman.company.Repository;

import by.roman.company.Entity.Technology;
import org.springframework.data.repository.CrudRepository;

public interface TechnologyRepository extends CrudRepository<Technology, Integer> {
}
