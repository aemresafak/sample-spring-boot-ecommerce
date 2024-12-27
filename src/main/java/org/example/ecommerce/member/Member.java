package org.example.ecommerce.member;

import org.example.ecommerce.customer.Customer;
import org.example.ecommerce.role.RoleEntity;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.stream.Collectors;

public record Member(String email, Collection<String> roles, @Nullable Customer customer) {
    public static Member from(MemberEntity entity) {
        var roles = entity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toUnmodifiableSet());
        Customer customer;
        if (entity.getCustomerEntity() != null) {
            customer = Customer.from(entity.getCustomerEntity());
        } else {
            customer = null;
        }
        return new Member(entity.getEmail(), roles, customer);
    }
}
