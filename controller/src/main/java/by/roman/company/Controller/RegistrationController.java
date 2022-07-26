package by.roman.company.Controller;

import by.roman.company.Entity.User;
import by.roman.company.Enum.Role;
import by.roman.company.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

import static by.roman.company.Constants.Constants.*;

@Controller
@RequestMapping(REGISTRATION_URL)
@RequiredArgsConstructor
public class RegistrationController {


    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public String newUser(User user, Model model) {
        model.addAttribute(USER_ATT, user);
        model.addAttribute("company", Role.ADMIN);
        model.addAttribute("users", Role.USER);
        return TO_URL_REGISTRATION;
    }

    @PostMapping
    public String addUser(User user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute(ERROR_ATT, ERROR_VALUE);
            return TO_URL_REGISTRATION;
        } else {
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);

        }
        return REDIRECT_LOGIN;
    }
}
