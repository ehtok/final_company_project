package by.roman.company.Controller;

import by.roman.company.Entity.Technology;
import by.roman.company.Service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

import static by.roman.company.Constants.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(TECHNOLOGY_URL)
public class TechnologyController {
    private final TechnologyService technologyService;

    @GetMapping
    public String findAllVacancies(Model model) {
        List<Technology> technologies = technologyService.findAllTechnologies();
        model.addAttribute(TECHNOLOGIES_ATT, technologies);
        return TO_URL_TECHNOLOGY;
    }

    @GetMapping(NEW_GET_MAPPING)
    public String addTechnology(Model model) {
        model.addAttribute(TECHNOLOGY_ATT, new Technology());
        return TO_URL_NEW_TECHNOLOGY;
    }

    @PostMapping
    public String saveTechnology(Technology technology) {
        technologyService.saveTechnology(technology);
        return REDIRECT_TECHNOLOGY;
    }

    @GetMapping(DELETE_ID_GET_MAPPING)
    public String deleteTechnology(@PathVariable(value = ID) Integer id) {
        String page;
        try {
            technologyService.deleteTechnology(Optional.ofNullable(id).orElse(null));
            page = REDIRECT_TECHNOLOGY;
        } catch (Exception e) {
            e.printStackTrace();
            page = TO_URL_TECHNOLOGY_ERROR;
        }

        return page;
    }

    @GetMapping(UPDATE_ID_GET_MAPPING)
    public String updateTechnology(@PathVariable(value = ID) Integer id, Model model) {
        Technology technology = technologyService.findTechnologyById(Optional.ofNullable(id).orElse(null));
        model.addAttribute(TECHNOLOGY_ATT, technology);
        return TO_URL_UPDATE_TECHNOLOGY;
    }
}
