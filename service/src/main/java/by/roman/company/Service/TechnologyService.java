package by.roman.company.Service;

import by.roman.company.Entity.Technology;

import java.util.List;

public interface TechnologyService {
    List<Technology> findAllTechnologies();

    Technology saveTechnology(Technology vacancy);

    Technology findTechnologyById(Integer id);

    void deleteTechnology(Integer id);
}
