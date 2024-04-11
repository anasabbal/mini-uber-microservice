package com.nas.authen.service.payload;


import com.nas.authen.service.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;


public class UserDetailsImpl implements UserDetails{
    private String userId;
    private String email;
    private String password;
    private Set<GrantedAuthority> authorities;
    public UserDetailsImpl(String userId, String email, String password, Set<GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(User client){
        String role = client.getRole().getRoleType().getDisplayName().toUpperCase();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        return new UserDetailsImpl(
                client.getId(),
                client.getEmail(),
                client.getPassword(),
                Collections.singleton(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
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
