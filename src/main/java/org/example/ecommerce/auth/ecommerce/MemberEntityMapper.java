package org.example.ecommerce.auth.ecommerce;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.ecommerce.member.MemberEntity;
import org.example.ecommerce.role.RoleEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class MemberEntityMapper {
    public static UserDetails toUserDetails(MemberEntity member) {
        var authorities = member.getRoles().stream().map(RoleEntity::getName).map(SimpleGrantedAuthority::new).toList();
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }
}
