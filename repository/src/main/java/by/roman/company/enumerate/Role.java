package by.roman.company.enumerate;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    SUPER;

    @Override
    public String getAuthority() {
        return name();
    }
}
