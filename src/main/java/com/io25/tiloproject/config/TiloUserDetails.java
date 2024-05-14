package com.io25.tiloproject.config;

import com.io25.tiloproject.model.TiloUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

public class TiloUserDetails implements UserDetails {

    private final TiloUser tiloUser;

    public TiloUserDetails(TiloUser tiloUser) {
        this.tiloUser = tiloUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(tiloUser.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return tiloUser.getPassword();
    }

    @Override
    public String getUsername() {
        return tiloUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
