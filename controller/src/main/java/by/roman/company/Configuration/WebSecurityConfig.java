package by.roman.company.Configuration;


import by.roman.company.Service.implement.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static by.roman.company.Constants.Constants.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(TO_HOME_URL, REGISTRATION_URL).permitAll()
                .antMatchers(UPDATE_ACCESS, NEW_ACCESS, TECHNOLOGY_URL).hasAnyAuthority(ROLE_ADMIN, ROLE_SUPER)
                .antMatchers(DELETE_ACCESS).hasAnyAuthority(ROLE_SUPER)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_URL)
                .defaultSuccessUrl(USER_URL)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }
}
