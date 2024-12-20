package org.example.ecommerce.auth;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.customer.CustomerEntity;
import org.example.ecommerce.customer.CustomerRepository;
import org.example.ecommerce.member.MemberEntity;
import org.example.ecommerce.member.MemberRepository;
import org.example.ecommerce.role.Role;
import org.example.ecommerce.role.RoleJpaService;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RoleJpaService roleJpaService;
    private final CustomerRepository customerRepository;

    @Transactional
    public void register(String email, String password, @Nullable CustomerDetails customerDetails) {
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
        var customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customerDetails.firstName());
        customerEntity.setLastName(customerDetails.lastName());
        customerEntity.setBirthDate(customerDetails.birthDate());
        return Optional.of(customerRepository.save(customerEntity));
    }
}
