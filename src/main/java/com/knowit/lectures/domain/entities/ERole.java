package com.knowit.lectures.domain.entities;


import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {

    EDITOR,
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}

