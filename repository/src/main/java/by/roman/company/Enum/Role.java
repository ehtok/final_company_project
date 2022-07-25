package by.roman.company.Enum;

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
