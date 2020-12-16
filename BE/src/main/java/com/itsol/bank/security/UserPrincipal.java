package com.itsol.bank.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SuppressWarnings("serial")
@Getter
@Setter
public class UserPrincipal implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    @SuppressWarnings("rawtypes")
	private Collection authorities;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    
    
}
