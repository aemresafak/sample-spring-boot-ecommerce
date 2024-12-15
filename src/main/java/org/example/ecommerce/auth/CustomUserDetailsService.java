package org.example.ecommerce.auth;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.member.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmailAndDeletedFalse(email)
                .map(MemberEntityMapper::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Email: %s not found.".formatted(email)));
    }

}
