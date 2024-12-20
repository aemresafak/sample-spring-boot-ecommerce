package org.example.ecommerce.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class RoleJpaService {
    private final RoleRepository roleRepository;

    public RoleEntity fetchRoleEntity(Role role) {
        return roleRepository
                .findByName(role.getRoleName())
                .orElseThrow(supplyInvalidRoleNameException(role.getRoleName()));
    }

    private static Supplier<IllegalStateException> supplyInvalidRoleNameException(String roleName) {
        return () -> new IllegalStateException(roleName + " could not be found in the database");
    }
}
