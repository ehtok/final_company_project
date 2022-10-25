package by.roman.company.сontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.roman.company.constants.Constants.HOME_URL;

@Controller
@RequiredArgsConstructor
@RequestMapping(HOME_URL)
public class MainController {


}
