package org.example.ecommerce.auth.ecommerce;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.auth.AuthTokens;
import org.example.ecommerce.auth.ecommerce.controller.CustomerDetailsDTO;
import org.example.ecommerce.auth.jwt.JWTService;
import org.example.ecommerce.auth.refreshtoken.RefreshTokenService;
import org.example.ecommerce.customer.CustomerEntity;
import org.example.ecommerce.customer.CustomerRepository;
import org.example.ecommerce.member.Member;
import org.example.ecommerce.member.MemberEntity;
import org.example.ecommerce.member.MemberRepository;
import org.example.ecommerce.role.Role;
import org.example.ecommerce.role.RoleJpaService;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RoleJpaService roleJpaService;
    private final CustomerRepository customerRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthTokens refresh(UUID tokenId, String refreshToken) {
        return refreshTokenService.refresh(tokenId, refreshToken);
    }

    @Transactional
    public AuthTokens login(String email, String password) {
        log.info("Trying login for {}", kv("email", email));
        var token = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(token);
        var member = Member.from(memberRepository.findByEmailAndDeletedFalse(email).orElseThrow());
        var accessToken = jwtService.generateToken(member);
        var refreshToken = refreshTokenService.createRefreshToken(member.id());
        return new AuthTokens(accessToken, refreshToken.id(), refreshToken.token());
    }

    @Transactional
    public void register(String email, String password, @Nullable CustomerDetailsDTO customerDetailsDTO) {
        log.info("Registering member {}", kv("email", email));
        memberRepository
                .findByEmailAndDeletedFalse(email)
                .ifPresent((member) -> {
                    throw new EmailAlreadyTakenException(email);
                });

        var roles = Set.of(roleJpaService.fetchRoleEntity(Role.USER));
        var memberEntity = new MemberEntity();
        memberEntity.setEmail(email);
        memberEntity.setPassword(passwordEncoder.encode(password));
        memberEntity.setRoles(roles);

        createCustomerIfNecessary(customerDetailsDTO)
                .ifPresent(memberEntity::setCustomerEntity);

        memberRepository.save(memberEntity);
    }

    private Optional<CustomerEntity> createCustomerIfNecessary(@Nullable CustomerDetailsDTO customerDetailsDTO) {
        if (customerDetailsDTO == null) return Optional.empty();
        log.info("Creating customer {} {}", kv("firstName", customerDetailsDTO.firstName()), kv("lastName", customerDetailsDTO.lastName()));
        var customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDetailsDTO.firstName());
        customerEntity.setLastName(customerDetailsDTO.lastName());
        customerEntity.setBirthDate(customerDetailsDTO.birthDate());
        return Optional.of(customerRepository.save(customerEntity));
    }
}
