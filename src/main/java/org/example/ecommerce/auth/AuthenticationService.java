package org.example.ecommerce.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.auth.controller.CustomerDetails;
import org.example.ecommerce.jwt.JWTService;
import org.example.ecommerce.customer.CustomerEntity;
import org.example.ecommerce.customer.CustomerRepository;
import org.example.ecommerce.member.MemberEntity;
import org.example.ecommerce.member.MemberRepository;
import org.example.ecommerce.role.Role;
import org.example.ecommerce.role.RoleJpaService;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String login(String email, String password) {
        log.info("Trying login for {}", kv("email", email));
        var token = new UsernamePasswordAuthenticationToken(email, password);
        var authentication = authenticationManager.authenticate(token);

        log.info("Generating JWT for {}", kv("email", email));
        var authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toUnmodifiableSet());
        return jwtService.generateToken(email, authorities);
    }

    @Transactional
    public void register(String email, String password, @Nullable CustomerDetails customerDetails) {
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

        createCustomerIfNecessary(customerDetails)
                .ifPresent(memberEntity::setCustomerEntity);

        memberRepository.save(memberEntity);
    }


    private Optional<CustomerEntity> createCustomerIfNecessary(@Nullable CustomerDetails customerDetails) {
        if (customerDetails == null) return Optional.empty();
        log.info("Creating customer {} {}", kv("firstName", customerDetails.firstName()), kv("lastName", customerDetails.lastName()));
        var customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDetails.firstName());
        customerEntity.setLastName(customerDetails.lastName());
        customerEntity.setBirthDate(customerDetails.birthDate());
        return Optional.of(customerRepository.save(customerEntity));
    }
}
