package jestebancdev.DisneyApi.enumeration;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/6/2022
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN;
    public String getAuthority() {
        return name();
    }
}
