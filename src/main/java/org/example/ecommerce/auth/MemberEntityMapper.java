package org.example.ecommerce.auth;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.ecommerce.member.MemberEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberEntityMapper {
    public static UserDetails toUserDetails(MemberEntity member) {
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .build();

    }
}
