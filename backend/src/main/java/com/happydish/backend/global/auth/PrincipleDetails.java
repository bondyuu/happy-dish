package com.happydish.backend.global.auth;

import com.happydish.backend.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class PrincipleDetails implements UserDetails, OAuth2User {
    private User user; //콤포지션
    private Map<String, Object> attributes;

    public PrincipleDetails(User user, Map<String, Object> attributes) { //OAuth 로그인
        this.user = user;
        this.attributes = attributes;
    }

    public PrincipleDetails(User user){ //일반로그인
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

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
