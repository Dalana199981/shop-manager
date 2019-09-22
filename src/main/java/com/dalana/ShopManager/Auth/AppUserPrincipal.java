package com.dalana.ShopManager.Auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserPrincipal implements UserDetails {
    User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList al = new ArrayList<>();
        al.add(new Authority(user));
        return al;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    public AppUserPrincipal(User user) {
        this.user = user;
    }
}

class Authority implements GrantedAuthority {
    User user;

    public Authority(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return user.getRole();
    }
}