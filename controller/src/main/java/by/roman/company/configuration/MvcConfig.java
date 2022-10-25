package by.roman.company.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static by.roman.company.constants.Constants.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(TO_HOME_URL).setViewName(HOME);
        registry.addViewController(LOGIN_URL).setViewName(LOGIN);
    }
}
