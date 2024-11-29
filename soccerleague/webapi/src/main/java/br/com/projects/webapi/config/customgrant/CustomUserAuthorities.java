package br.com.projects.webapi.config.customgrant;

import java.util.Collection;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class CustomUserAuthorities {

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserAuthorities(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }
}
