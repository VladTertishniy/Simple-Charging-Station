package com.extrawest.simplechargingstationtertyshniy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Role {
    SELLER(Set.of(Permission.USERS_UPDATE, Permission.USERS_CREATE)),
    BUYER(Set.of(Permission.USERS_READ)),
    MANAGER(Set.of(Permission.USERS_UPDATE)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_UPDATE, Permission.USERS_REMOVE));

    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getPermission()))
                .collect(Collectors.toSet());
    }
}
