package by.roman.company.service;

import by.roman.company.entity.Technology;

import java.util.List;

public interface TechnologyService {
    List<Technology> findAllTechnologies();

    Technology saveTechnology(Technology vacancy);

    Technology findTechnologyById(Integer id);

    void deleteTechnology(Integer id);
}
