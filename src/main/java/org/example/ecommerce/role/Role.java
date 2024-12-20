package org.example.ecommerce.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private final String roleName;

    public static Role fromEntity(RoleEntity roleEntity) {
        return Arrays.stream(Role.values())
                .filter(role -> role.roleName.equals(roleEntity.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Role name not found for " + roleEntity.getId()));
    }
}
