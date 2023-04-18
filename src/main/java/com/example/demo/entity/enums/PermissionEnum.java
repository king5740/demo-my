package com.example.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum PermissionEnum implements GrantedAuthority {
    ADD_ORDER,
    EDIT_ORDER,
    DELETE_ORDER,
    GET_ORDER,
    GET_ORDERS;

    @Override
    public String getAuthority() {
        return name();
    }
}
