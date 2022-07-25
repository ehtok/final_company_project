package by.roman.company.Service.implement;

import by.roman.company.Entity.Technology;
import by.roman.company.Repository.TechnologyRepository;
import by.roman.company.Service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Override
    public List<Technology> findAllTechnologies() {

        return (List<Technology>) technologyRepository.findAll();
    }

    @Override
    public Technology saveTechnology(Technology vacancy) {
        return technologyRepository.save(vacancy);
    }

    @Override
    public Technology findTechnologyById(Integer id) {
        return technologyRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteTechnology(Integer id) {
        technologyRepository.deleteById(id);
    }
}
